/*     */ package com.sun.activation.registries;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MailcapFile
/*     */ {
/*  24 */   private Map type_hash = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  29 */   private Map fallback_hash = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private Map native_commands = new HashMap<>();
/*     */   
/*     */   private static boolean addReverse = false;
/*     */   
/*     */   static {
/*     */     try {
/*  41 */       addReverse = Boolean.getBoolean("javax.activation.addreverse");
/*  42 */     } catch (Throwable throwable) {}
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
/*     */   public MailcapFile(String new_fname) throws IOException {
/*  54 */     if (LogSupport.isLoggable())
/*  55 */       LogSupport.log("new MailcapFile: file " + new_fname); 
/*  56 */     FileReader reader = null;
/*     */     try {
/*  58 */       reader = new FileReader(new_fname);
/*  59 */       parse(new BufferedReader(reader));
/*     */     } finally {
/*  61 */       if (reader != null) {
/*     */         try {
/*  63 */           reader.close();
/*  64 */         } catch (IOException iOException) {}
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
/*     */   public MailcapFile(InputStream is) throws IOException {
/*  76 */     if (LogSupport.isLoggable())
/*  77 */       LogSupport.log("new MailcapFile: InputStream"); 
/*  78 */     parse(new BufferedReader(new InputStreamReader(is, "iso-8859-1")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MailcapFile() {
/*  85 */     if (LogSupport.isLoggable()) {
/*  86 */       LogSupport.log("new MailcapFile: default");
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
/*     */   public Map getMailcapList(String mime_type) {
/* 101 */     Map search_result = null;
/* 102 */     Map wildcard_result = null;
/*     */ 
/*     */     
/* 105 */     search_result = (Map)this.type_hash.get(mime_type);
/*     */ 
/*     */     
/* 108 */     int separator = mime_type.indexOf('/');
/* 109 */     String subtype = mime_type.substring(separator + 1);
/* 110 */     if (!subtype.equals("*")) {
/* 111 */       String type = mime_type.substring(0, separator + 1) + "*";
/* 112 */       wildcard_result = (Map)this.type_hash.get(type);
/*     */       
/* 114 */       if (wildcard_result != null)
/* 115 */         if (search_result != null) {
/*     */           
/* 117 */           search_result = mergeResults(search_result, wildcard_result);
/*     */         } else {
/* 119 */           search_result = wildcard_result;
/*     */         }  
/*     */     } 
/* 122 */     return search_result;
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
/*     */   public Map getMailcapFallbackList(String mime_type) {
/* 137 */     Map search_result = null;
/* 138 */     Map wildcard_result = null;
/*     */ 
/*     */     
/* 141 */     search_result = (Map)this.fallback_hash.get(mime_type);
/*     */ 
/*     */     
/* 144 */     int separator = mime_type.indexOf('/');
/* 145 */     String subtype = mime_type.substring(separator + 1);
/* 146 */     if (!subtype.equals("*")) {
/* 147 */       String type = mime_type.substring(0, separator + 1) + "*";
/* 148 */       wildcard_result = (Map)this.fallback_hash.get(type);
/*     */       
/* 150 */       if (wildcard_result != null)
/* 151 */         if (search_result != null) {
/*     */           
/* 153 */           search_result = mergeResults(search_result, wildcard_result);
/*     */         } else {
/* 155 */           search_result = wildcard_result;
/*     */         }  
/*     */     } 
/* 158 */     return search_result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getMimeTypes() {
/* 167 */     Set types = new HashSet(this.type_hash.keySet());
/* 168 */     types.addAll(this.fallback_hash.keySet());
/* 169 */     types.addAll(this.native_commands.keySet());
/* 170 */     String[] mts = new String[types.size()];
/* 171 */     mts = (String[])types.toArray((Object[])mts);
/* 172 */     return mts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getNativeCommands(String mime_type) {
/* 182 */     String[] cmds = null;
/*     */     
/* 184 */     List v = (List)this.native_commands.get(mime_type.toLowerCase(Locale.ENGLISH));
/* 185 */     if (v != null) {
/* 186 */       cmds = new String[v.size()];
/* 187 */       cmds = (String[])v.toArray((Object[])cmds);
/*     */     } 
/* 189 */     return cmds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map mergeResults(Map<?, ?> first, Map second) {
/* 199 */     Iterator<String> verb_enum = second.keySet().iterator();
/* 200 */     Map<Object, Object> clonedHash = new HashMap<>(first);
/*     */ 
/*     */     
/* 203 */     while (verb_enum.hasNext()) {
/* 204 */       String verb = verb_enum.next();
/* 205 */       List cmdVector = (List)clonedHash.get(verb);
/* 206 */       if (cmdVector == null) {
/* 207 */         clonedHash.put(verb, second.get(verb));
/*     */         continue;
/*     */       } 
/* 210 */       List<?> oldV = (List)second.get(verb);
/* 211 */       cmdVector = new ArrayList(cmdVector);
/* 212 */       cmdVector.addAll(oldV);
/* 213 */       clonedHash.put(verb, cmdVector);
/*     */     } 
/*     */     
/* 216 */     return clonedHash;
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
/*     */   public void appendToMailcap(String mail_cap) {
/* 232 */     if (LogSupport.isLoggable())
/* 233 */       LogSupport.log("appendToMailcap: " + mail_cap); 
/*     */     try {
/* 235 */       parse(new StringReader(mail_cap));
/* 236 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parse(Reader reader) throws IOException {
/* 245 */     BufferedReader buf_reader = new BufferedReader(reader);
/* 246 */     String line = null;
/* 247 */     String continued = null;
/*     */     
/* 249 */     while ((line = buf_reader.readLine()) != null) {
/*     */ 
/*     */       
/* 252 */       line = line.trim();
/*     */       
/*     */       try {
/* 255 */         if (line.charAt(0) == '#')
/*     */           continue; 
/* 257 */         if (line.charAt(line.length() - 1) == '\\') {
/* 258 */           if (continued != null) {
/* 259 */             continued = continued + continued; continue;
/*     */           } 
/* 261 */           continued = line.substring(0, line.length() - 1); continue;
/* 262 */         }  if (continued != null) {
/*     */           
/* 264 */           continued = continued + continued;
/*     */           
/*     */           try {
/* 267 */             parseLine(continued);
/* 268 */           } catch (MailcapParseException mailcapParseException) {}
/*     */ 
/*     */           
/* 271 */           continued = null;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         try {
/* 276 */           parseLine(line);
/*     */         }
/* 278 */         catch (MailcapParseException mailcapParseException) {}
/*     */ 
/*     */       
/*     */       }
/* 282 */       catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {}
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
/*     */   protected void parseLine(String mailcapEntry) throws MailcapParseException, IOException {
/* 298 */     MailcapTokenizer tokenizer = new MailcapTokenizer(mailcapEntry);
/* 299 */     tokenizer.setIsAutoquoting(false);
/*     */     
/* 301 */     if (LogSupport.isLoggable()) {
/* 302 */       LogSupport.log("parse: " + mailcapEntry);
/*     */     }
/* 304 */     int currentToken = tokenizer.nextToken();
/* 305 */     if (currentToken != 2) {
/* 306 */       reportParseError(2, currentToken, tokenizer
/* 307 */           .getCurrentTokenValue());
/*     */     }
/*     */     
/* 310 */     String primaryType = tokenizer.getCurrentTokenValue().toLowerCase(Locale.ENGLISH);
/* 311 */     String subType = "*";
/*     */ 
/*     */ 
/*     */     
/* 315 */     currentToken = tokenizer.nextToken();
/* 316 */     if (currentToken != 47 && currentToken != 59)
/*     */     {
/* 318 */       reportParseError(47, 59, currentToken, tokenizer
/*     */           
/* 320 */           .getCurrentTokenValue());
/*     */     }
/*     */ 
/*     */     
/* 324 */     if (currentToken == 47) {
/*     */       
/* 326 */       currentToken = tokenizer.nextToken();
/* 327 */       if (currentToken != 2) {
/* 328 */         reportParseError(2, currentToken, tokenizer
/* 329 */             .getCurrentTokenValue());
/*     */       }
/*     */       
/* 332 */       subType = tokenizer.getCurrentTokenValue().toLowerCase(Locale.ENGLISH);
/*     */ 
/*     */       
/* 335 */       currentToken = tokenizer.nextToken();
/*     */     } 
/*     */     
/* 338 */     String mimeType = primaryType + "/" + primaryType;
/*     */     
/* 340 */     if (LogSupport.isLoggable()) {
/* 341 */       LogSupport.log("  Type: " + mimeType);
/*     */     }
/*     */     
/* 344 */     Map<Object, Object> commands = new LinkedHashMap<>();
/*     */ 
/*     */     
/* 347 */     if (currentToken != 59) {
/* 348 */       reportParseError(59, currentToken, tokenizer
/* 349 */           .getCurrentTokenValue());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 354 */     tokenizer.setIsAutoquoting(true);
/* 355 */     currentToken = tokenizer.nextToken();
/* 356 */     tokenizer.setIsAutoquoting(false);
/* 357 */     if (currentToken != 2 && currentToken != 59)
/*     */     {
/* 359 */       reportParseError(2, 59, currentToken, tokenizer
/*     */           
/* 361 */           .getCurrentTokenValue());
/*     */     }
/*     */     
/* 364 */     if (currentToken == 2) {
/*     */ 
/*     */       
/* 367 */       List<String> v = (List)this.native_commands.get(mimeType);
/* 368 */       if (v == null) {
/* 369 */         v = new ArrayList();
/* 370 */         v.add(mailcapEntry);
/* 371 */         this.native_commands.put(mimeType, v);
/*     */       } else {
/*     */         
/* 374 */         v.add(mailcapEntry);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 379 */     if (currentToken != 59) {
/* 380 */       currentToken = tokenizer.nextToken();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 385 */     if (currentToken == 59) {
/* 386 */       boolean isFallback = false;
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 391 */         currentToken = tokenizer.nextToken();
/* 392 */         if (currentToken != 2) {
/* 393 */           reportParseError(2, currentToken, tokenizer
/* 394 */               .getCurrentTokenValue());
/*     */         }
/*     */         
/* 397 */         String paramName = tokenizer.getCurrentTokenValue().toLowerCase(Locale.ENGLISH);
/*     */ 
/*     */         
/* 400 */         currentToken = tokenizer.nextToken();
/* 401 */         if (currentToken != 61 && currentToken != 59 && currentToken != 5)
/*     */         {
/*     */           
/* 404 */           reportParseError(61, 59, 5, currentToken, tokenizer
/*     */ 
/*     */               
/* 407 */               .getCurrentTokenValue());
/*     */         }
/*     */ 
/*     */         
/* 411 */         if (currentToken != 61) {
/*     */           continue;
/*     */         }
/*     */         
/* 415 */         tokenizer.setIsAutoquoting(true);
/* 416 */         currentToken = tokenizer.nextToken();
/* 417 */         tokenizer.setIsAutoquoting(false);
/* 418 */         if (currentToken != 2) {
/* 419 */           reportParseError(2, currentToken, tokenizer
/* 420 */               .getCurrentTokenValue());
/*     */         }
/*     */         
/* 423 */         String paramValue = tokenizer.getCurrentTokenValue();
/*     */ 
/*     */         
/* 426 */         if (paramName.startsWith("x-java-")) {
/* 427 */           String commandName = paramName.substring(7);
/*     */ 
/*     */           
/* 430 */           if (commandName.equals("fallback-entry") && paramValue
/* 431 */             .equalsIgnoreCase("true")) {
/* 432 */             isFallback = true;
/*     */           }
/*     */           else {
/*     */             
/* 436 */             if (LogSupport.isLoggable()) {
/* 437 */               LogSupport.log("    Command: " + commandName + ", Class: " + paramValue);
/*     */             }
/* 439 */             List<String> classes = (List)commands.get(commandName);
/* 440 */             if (classes == null) {
/* 441 */               classes = new ArrayList();
/* 442 */               commands.put(commandName, classes);
/*     */             } 
/* 444 */             if (addReverse) {
/* 445 */               classes.add(0, paramValue);
/*     */             } else {
/* 447 */               classes.add(paramValue);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 452 */         currentToken = tokenizer.nextToken();
/*     */       }
/* 454 */       while (currentToken == 59);
/*     */       
/* 456 */       Map<String, Map<Object, Object>> masterHash = isFallback ? this.fallback_hash : this.type_hash;
/*     */       
/* 458 */       Map<String, List> curcommands = (Map)masterHash.get(mimeType);
/* 459 */       if (curcommands == null) {
/* 460 */         masterHash.put(mimeType, commands);
/*     */       } else {
/* 462 */         if (LogSupport.isLoggable()) {
/* 463 */           LogSupport.log("Merging commands for type " + mimeType);
/*     */         }
/*     */         
/* 466 */         Iterator<String> cn = curcommands.keySet().iterator();
/* 467 */         while (cn.hasNext()) {
/* 468 */           String cmdName = cn.next();
/* 469 */           List<String> ccv = (List)curcommands.get(cmdName);
/* 470 */           List cv = (List)commands.get(cmdName);
/* 471 */           if (cv == null) {
/*     */             continue;
/*     */           }
/* 474 */           Iterator<String> cvn = cv.iterator();
/* 475 */           while (cvn.hasNext()) {
/* 476 */             String clazz = cvn.next();
/* 477 */             if (!ccv.contains(clazz)) {
/* 478 */               if (addReverse) {
/* 479 */                 ccv.add(0, clazz); continue;
/*     */               } 
/* 481 */               ccv.add(clazz);
/*     */             } 
/*     */           } 
/*     */         } 
/* 485 */         cn = (Iterator) commands.keySet().iterator();
/* 486 */         while (cn.hasNext()) {
/* 487 */           String cmdName = cn.next();
/* 488 */           if (curcommands.containsKey(cmdName))
/*     */             continue; 
/* 490 */           List cv = (List)commands.get(cmdName);
/* 491 */           curcommands.put(cmdName, cv);
/*     */         } 
/*     */       } 
/* 494 */     } else if (currentToken != 5) {
/* 495 */       reportParseError(5, 59, currentToken, tokenizer
/*     */           
/* 497 */           .getCurrentTokenValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void reportParseError(int expectedToken, int actualToken, String actualTokenValue) throws MailcapParseException {
/* 503 */     throw new MailcapParseException("Encountered a " + 
/* 504 */         MailcapTokenizer.nameForToken(actualToken) + " token (" + actualTokenValue + ") while expecting a " + 
/*     */         
/* 506 */         MailcapTokenizer.nameForToken(expectedToken) + " token.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void reportParseError(int expectedToken, int otherExpectedToken, int actualToken, String actualTokenValue) throws MailcapParseException {
/* 512 */     throw new MailcapParseException("Encountered a " + 
/* 513 */         MailcapTokenizer.nameForToken(actualToken) + " token (" + actualTokenValue + ") while expecting a " + 
/*     */         
/* 515 */         MailcapTokenizer.nameForToken(expectedToken) + " or a " + 
/* 516 */         MailcapTokenizer.nameForToken(otherExpectedToken) + " token.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void reportParseError(int expectedToken, int otherExpectedToken, int anotherExpectedToken, int actualToken, String actualTokenValue) throws MailcapParseException {
/* 522 */     if (LogSupport.isLoggable())
/* 523 */       LogSupport.log("PARSE ERROR: Encountered a " + 
/* 524 */           MailcapTokenizer.nameForToken(actualToken) + " token (" + actualTokenValue + ") while expecting a " + 
/*     */           
/* 526 */           MailcapTokenizer.nameForToken(expectedToken) + ", a " + 
/* 527 */           MailcapTokenizer.nameForToken(otherExpectedToken) + ", or a " + 
/* 528 */           MailcapTokenizer.nameForToken(anotherExpectedToken) + " token."); 
/* 529 */     throw new MailcapParseException("Encountered a " + 
/* 530 */         MailcapTokenizer.nameForToken(actualToken) + " token (" + actualTokenValue + ") while expecting a " + 
/*     */         
/* 532 */         MailcapTokenizer.nameForToken(expectedToken) + ", a " + 
/* 533 */         MailcapTokenizer.nameForToken(otherExpectedToken) + ", or a " + 
/* 534 */         MailcapTokenizer.nameForToken(anotherExpectedToken) + " token.");
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\registries\MailcapFile.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */