/*     */ package javax.activation;
/*     */ 
/*     */ import com.sun.activation.registries.LogSupport;
/*     */ import com.sun.activation.registries.MailcapFile;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MailcapCommandMap
/*     */   extends CommandMap
/*     */ {
/*     */   private MailcapFile[] DB;
/*     */   private static final int PROG = 0;
/*     */   private static final String confDir;
/*     */   
/*     */   static {
/* 120 */     String dir = null;
/*     */     try {
/* 122 */       dir = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */           {
/*     */             public String run() {
/* 125 */               String home = System.getProperty("java.home");
/* 126 */               String newdir = home + home + "conf";
/* 127 */               File conf = new File(newdir);
/* 128 */               if (conf.exists()) {
/* 129 */                 return newdir + newdir;
/*     */               }
/* 131 */               return home + home + "lib" + File.separator;
/*     */             }
/*     */           });
/* 134 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 137 */     confDir = dir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MailcapCommandMap() {
/* 145 */     List<MailcapFile> dbv = new ArrayList(5);
/* 146 */     MailcapFile mf = null;
/* 147 */     dbv.add(null);
/*     */     
/* 149 */     LogSupport.log("MailcapCommandMap: load HOME");
/*     */     try {
/* 151 */       String user_home = System.getProperty("user.home");
/*     */       
/* 153 */       if (user_home != null) {
/* 154 */         String path = user_home + user_home + ".mailcap";
/* 155 */         mf = loadFile(path);
/* 156 */         if (mf != null)
/* 157 */           dbv.add(mf); 
/*     */       } 
/* 159 */     } catch (SecurityException securityException) {}
/*     */     
/* 161 */     LogSupport.log("MailcapCommandMap: load SYS");
/*     */     
/*     */     try {
/* 164 */       if (confDir != null) {
/* 165 */         mf = loadFile(confDir + "mailcap");
/* 166 */         if (mf != null)
/* 167 */           dbv.add(mf); 
/*     */       } 
/* 169 */     } catch (SecurityException securityException) {}
/*     */     
/* 171 */     LogSupport.log("MailcapCommandMap: load JAR");
/*     */     
/* 173 */     loadAllResources(dbv, "META-INF/mailcap");
/*     */     
/* 175 */     LogSupport.log("MailcapCommandMap: load DEF");
/* 176 */     mf = loadResource("/META-INF/mailcap.default");
/*     */     
/* 178 */     if (mf != null) {
/* 179 */       dbv.add(mf);
/*     */     }
/* 181 */     this.DB = new MailcapFile[dbv.size()];
/* 182 */     this.DB = dbv.<MailcapFile>toArray(this.DB);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MailcapFile loadResource(String name) {
/* 189 */     InputStream clis = null;
/*     */     try {
/* 191 */       clis = SecuritySupport.getResourceAsStream(getClass(), name);
/* 192 */       if (clis != null) {
/* 193 */         MailcapFile mf = new MailcapFile(clis);
/* 194 */         if (LogSupport.isLoggable()) {
/* 195 */           LogSupport.log("MailcapCommandMap: successfully loaded mailcap file: " + name);
/*     */         }
/* 197 */         return mf;
/*     */       } 
/* 199 */       if (LogSupport.isLoggable()) {
/* 200 */         LogSupport.log("MailcapCommandMap: not loading mailcap file: " + name);
/*     */       }
/*     */     }
/* 203 */     catch (IOException e) {
/* 204 */       if (LogSupport.isLoggable())
/* 205 */         LogSupport.log("MailcapCommandMap: can't load " + name, e); 
/* 206 */     } catch (SecurityException sex) {
/* 207 */       if (LogSupport.isLoggable())
/* 208 */         LogSupport.log("MailcapCommandMap: can't load " + name, sex); 
/*     */     } finally {
/*     */       try {
/* 211 */         if (clis != null)
/* 212 */           clis.close(); 
/* 213 */       } catch (IOException iOException) {}
/*     */     } 
/* 215 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadAllResources(List<MailcapFile> v, String name) {
/* 222 */     boolean anyLoaded = false;
/*     */     try {
/*     */       URL[] urls;
/* 225 */       ClassLoader cld = null;
/*     */       
/* 227 */       cld = SecuritySupport.getContextClassLoader();
/* 228 */       if (cld == null)
/* 229 */         cld = getClass().getClassLoader(); 
/* 230 */       if (cld != null) {
/* 231 */         urls = SecuritySupport.getResources(cld, name);
/*     */       } else {
/* 233 */         urls = SecuritySupport.getSystemResources(name);
/* 234 */       }  if (urls != null) {
/* 235 */         if (LogSupport.isLoggable())
/* 236 */           LogSupport.log("MailcapCommandMap: getResources"); 
/* 237 */         for (int i = 0; i < urls.length; i++) {
/* 238 */           URL url = urls[i];
/* 239 */           InputStream clis = null;
/* 240 */           if (LogSupport.isLoggable());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 274 */     catch (Exception ex) {
/* 275 */       if (LogSupport.isLoggable()) {
/* 276 */         LogSupport.log("MailcapCommandMap: can't load " + name, ex);
/*     */       }
/*     */     } 
/*     */     
/* 280 */     if (!anyLoaded) {
/* 281 */       if (LogSupport.isLoggable())
/* 282 */         LogSupport.log("MailcapCommandMap: !anyLoaded"); 
/* 283 */       MailcapFile mf = loadResource("/" + name);
/* 284 */       if (mf != null) {
/* 285 */         v.add(mf);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private MailcapFile loadFile(String name) {
/* 293 */     MailcapFile mtf = null;
/*     */     
/*     */     try {
/* 296 */       mtf = new MailcapFile(name);
/* 297 */     } catch (IOException iOException) {}
/*     */ 
/*     */     
/* 300 */     return mtf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MailcapCommandMap(String fileName) throws IOException {
/* 311 */     this();
/*     */     
/* 313 */     if (LogSupport.isLoggable())
/* 314 */       LogSupport.log("MailcapCommandMap: load PROG from " + fileName); 
/* 315 */     if (this.DB[0] == null) {
/* 316 */       this.DB[0] = new MailcapFile(fileName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MailcapCommandMap(InputStream is) {
/* 328 */     this();
/*     */     
/* 330 */     LogSupport.log("MailcapCommandMap: load PROG");
/* 331 */     if (this.DB[0] == null) {
/*     */       try {
/* 333 */         this.DB[0] = new MailcapFile(is);
/* 334 */       } catch (IOException iOException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized CommandInfo[] getPreferredCommands(String mimeType) {
/* 354 */     List cmdList = new ArrayList();
/* 355 */     if (mimeType != null)
/* 356 */       mimeType = mimeType.toLowerCase(Locale.ENGLISH); 
/*     */     int i;
/* 358 */     for (i = 0; i < this.DB.length; i++) {
/* 359 */       if (this.DB[i] != null) {
/*     */         
/* 361 */         Map cmdMap = this.DB[i].getMailcapList(mimeType);
/* 362 */         if (cmdMap != null) {
/* 363 */           appendPrefCmdsToList(cmdMap, cmdList);
/*     */         }
/*     */       } 
/*     */     } 
/* 367 */     for (i = 0; i < this.DB.length; i++) {
/* 368 */       if (this.DB[i] != null) {
/*     */         
/* 370 */         Map cmdMap = this.DB[i].getMailcapFallbackList(mimeType);
/* 371 */         if (cmdMap != null)
/* 372 */           appendPrefCmdsToList(cmdMap, cmdList); 
/*     */       } 
/*     */     } 
/* 375 */     CommandInfo[] cmdInfos = new CommandInfo[cmdList.size()];
/* 376 */     cmdInfos = (CommandInfo[])cmdList.toArray((Object[])cmdInfos);
/*     */     
/* 378 */     return cmdInfos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void appendPrefCmdsToList(Map cmdHash, List<CommandInfo> cmdList) {
/* 385 */     Iterator<String> verb_enum = cmdHash.keySet().iterator();
/*     */     
/* 387 */     while (verb_enum.hasNext()) {
/* 388 */       String verb = verb_enum.next();
/* 389 */       if (!checkForVerb(cmdList, verb)) {
/* 390 */         List<String> cmdList2 = (List)cmdHash.get(verb);
/* 391 */         String className = cmdList2.get(0);
/* 392 */         cmdList.add(new CommandInfo(verb, className));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean checkForVerb(List cmdList, String verb) {
/* 402 */     Iterator<CommandInfo> ee = cmdList.iterator();
/* 403 */     while (ee.hasNext()) {
/*     */       
/* 405 */       String enum_verb = ((CommandInfo)ee.next()).getCommandName();
/* 406 */       if (enum_verb.equals(verb))
/* 407 */         return true; 
/*     */     } 
/* 409 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized CommandInfo[] getAllCommands(String mimeType) {
/* 420 */     List cmdList = new ArrayList();
/* 421 */     if (mimeType != null)
/* 422 */       mimeType = mimeType.toLowerCase(Locale.ENGLISH); 
/*     */     int i;
/* 424 */     for (i = 0; i < this.DB.length; i++) {
/* 425 */       if (this.DB[i] != null) {
/*     */         
/* 427 */         Map cmdMap = this.DB[i].getMailcapList(mimeType);
/* 428 */         if (cmdMap != null) {
/* 429 */           appendCmdsToList(cmdMap, cmdList);
/*     */         }
/*     */       } 
/*     */     } 
/* 433 */     for (i = 0; i < this.DB.length; i++) {
/* 434 */       if (this.DB[i] != null) {
/*     */         
/* 436 */         Map cmdMap = this.DB[i].getMailcapFallbackList(mimeType);
/* 437 */         if (cmdMap != null)
/* 438 */           appendCmdsToList(cmdMap, cmdList); 
/*     */       } 
/*     */     } 
/* 441 */     CommandInfo[] cmdInfos = new CommandInfo[cmdList.size()];
/* 442 */     cmdInfos = (CommandInfo[])cmdList.toArray((Object[])cmdInfos);
/*     */     
/* 444 */     return cmdInfos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void appendCmdsToList(Map typeHash, List<CommandInfo> cmdList) {
/* 451 */     Iterator<String> verb_enum = typeHash.keySet().iterator();
/*     */     
/* 453 */     while (verb_enum.hasNext()) {
/* 454 */       String verb = verb_enum.next();
/* 455 */       List cmdList2 = (List)typeHash.get(verb);
/* 456 */       Iterator<String> cmd_enum = cmdList2.iterator();
/*     */       
/* 458 */       while (cmd_enum.hasNext()) {
/* 459 */         String cmd = cmd_enum.next();
/* 460 */         cmdList.add(new CommandInfo(verb, cmd));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized CommandInfo getCommand(String mimeType, String cmdName) {
/* 475 */     if (mimeType != null)
/* 476 */       mimeType = mimeType.toLowerCase(Locale.ENGLISH); 
/*     */     int i;
/* 478 */     for (i = 0; i < this.DB.length; i++) {
/* 479 */       if (this.DB[i] != null) {
/*     */         
/* 481 */         Map cmdMap = this.DB[i].getMailcapList(mimeType);
/* 482 */         if (cmdMap != null) {
/*     */           
/* 484 */           List<String> v = (List)cmdMap.get(cmdName);
/* 485 */           if (v != null) {
/* 486 */             String cmdClassName = v.get(0);
/*     */             
/* 488 */             if (cmdClassName != null) {
/* 489 */               return new CommandInfo(cmdName, cmdClassName);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 495 */     for (i = 0; i < this.DB.length; i++) {
/* 496 */       if (this.DB[i] != null) {
/*     */         
/* 498 */         Map cmdMap = this.DB[i].getMailcapFallbackList(mimeType);
/* 499 */         if (cmdMap != null) {
/*     */           
/* 501 */           List<String> v = (List)cmdMap.get(cmdName);
/* 502 */           if (v != null) {
/* 503 */             String cmdClassName = v.get(0);
/*     */             
/* 505 */             if (cmdClassName != null)
/* 506 */               return new CommandInfo(cmdName, cmdClassName); 
/*     */           } 
/*     */         } 
/*     */       } 
/* 510 */     }  return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addMailcap(String mail_cap) {
/* 524 */     LogSupport.log("MailcapCommandMap: add to PROG");
/* 525 */     if (this.DB[0] == null) {
/* 526 */       this.DB[0] = new MailcapFile();
/*     */     }
/* 528 */     this.DB[0].appendToMailcap(mail_cap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized DataContentHandler createDataContentHandler(String mimeType) {
/* 539 */     if (LogSupport.isLoggable()) {
/* 540 */       LogSupport.log("MailcapCommandMap: createDataContentHandler for " + mimeType);
/*     */     }
/* 542 */     if (mimeType != null)
/* 543 */       mimeType = mimeType.toLowerCase(Locale.ENGLISH); 
/*     */     int i;
/* 545 */     for (i = 0; i < this.DB.length; i++) {
/* 546 */       if (this.DB[i] != null) {
/*     */         
/* 548 */         if (LogSupport.isLoggable())
/* 549 */           LogSupport.log("  search DB #" + i); 
/* 550 */         Map cmdMap = this.DB[i].getMailcapList(mimeType);
/* 551 */         if (cmdMap != null) {
/* 552 */           List<String> v = (List)cmdMap.get("content-handler");
/* 553 */           if (v != null) {
/* 554 */             String name = v.get(0);
/* 555 */             DataContentHandler dch = getDataContentHandler(name);
/* 556 */             if (dch != null) {
/* 557 */               return dch;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 563 */     for (i = 0; i < this.DB.length; i++) {
/* 564 */       if (this.DB[i] != null) {
/*     */         
/* 566 */         if (LogSupport.isLoggable())
/* 567 */           LogSupport.log("  search fallback DB #" + i); 
/* 568 */         Map cmdMap = this.DB[i].getMailcapFallbackList(mimeType);
/* 569 */         if (cmdMap != null) {
/* 570 */           List<String> v = (List)cmdMap.get("content-handler");
/* 571 */           if (v != null) {
/* 572 */             String name = v.get(0);
/* 573 */             DataContentHandler dch = getDataContentHandler(name);
/* 574 */             if (dch != null)
/* 575 */               return dch; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 579 */     }  return null;
/*     */   }
/*     */   
/*     */   private DataContentHandler getDataContentHandler(String name) {
/* 583 */     if (LogSupport.isLoggable())
/* 584 */       LogSupport.log("    got content-handler"); 
/* 585 */     if (LogSupport.isLoggable())
/* 586 */       LogSupport.log("      class " + name); 
/*     */     try {
/* 588 */       ClassLoader cld = null;
/*     */       
/* 590 */       cld = SecuritySupport.getContextClassLoader();
/* 591 */       if (cld == null)
/* 592 */         cld = getClass().getClassLoader(); 
/* 593 */       Class<?> cl = null;
/*     */       try {
/* 595 */         cl = cld.loadClass(name);
/* 596 */       } catch (Exception ex) {
/*     */         
/* 598 */         cl = Class.forName(name);
/*     */       } 
/* 600 */       if (cl != null)
/* 601 */         return (DataContentHandler)cl.newInstance(); 
/* 602 */     } catch (IllegalAccessException e) {
/* 603 */       if (LogSupport.isLoggable())
/* 604 */         LogSupport.log("Can't load DCH " + name, e); 
/* 605 */     } catch (ClassNotFoundException e) {
/* 606 */       if (LogSupport.isLoggable())
/* 607 */         LogSupport.log("Can't load DCH " + name, e); 
/* 608 */     } catch (InstantiationException e) {
/* 609 */       if (LogSupport.isLoggable())
/* 610 */         LogSupport.log("Can't load DCH " + name, e); 
/*     */     } 
/* 612 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String[] getMimeTypes() {
/* 622 */     List<String> mtList = new ArrayList();
/*     */     
/* 624 */     for (int i = 0; i < this.DB.length; i++) {
/* 625 */       if (this.DB[i] != null) {
/*     */         
/* 627 */         String[] ts = this.DB[i].getMimeTypes();
/* 628 */         if (ts != null)
/* 629 */           for (int j = 0; j < ts.length; j++) {
/*     */             
/* 631 */             if (!mtList.contains(ts[j])) {
/* 632 */               mtList.add(ts[j]);
/*     */             }
/*     */           }  
/*     */       } 
/*     */     } 
/* 637 */     String[] mts = new String[mtList.size()];
/* 638 */     mts = mtList.<String>toArray(mts);
/*     */     
/* 640 */     return mts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String[] getNativeCommands(String mimeType) {
/* 659 */     List<String> cmdList = new ArrayList();
/* 660 */     if (mimeType != null) {
/* 661 */       mimeType = mimeType.toLowerCase(Locale.ENGLISH);
/*     */     }
/* 663 */     for (int i = 0; i < this.DB.length; i++) {
/* 664 */       if (this.DB[i] != null) {
/*     */         
/* 666 */         String[] arrayOfString = this.DB[i].getNativeCommands(mimeType);
/* 667 */         if (arrayOfString != null)
/* 668 */           for (int j = 0; j < arrayOfString.length; j++) {
/*     */             
/* 670 */             if (!cmdList.contains(arrayOfString[j])) {
/* 671 */               cmdList.add(arrayOfString[j]);
/*     */             }
/*     */           }  
/*     */       } 
/*     */     } 
/* 676 */     String[] cmds = new String[cmdList.size()];
/* 677 */     cmds = cmdList.<String>toArray(cmds);
/*     */     
/* 679 */     return cmds;
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\MailcapCommandMap.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */