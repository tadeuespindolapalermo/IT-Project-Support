/*     */ package javax.activation;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
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
/*     */ public class CommandInfo
/*     */ {
/*     */   private String verb;
/*     */   private String className;
/*     */   
/*     */   public CommandInfo(String verb, String className) {
/*  43 */     this.verb = verb;
/*  44 */     this.className = className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCommandName() {
/*  53 */     return this.verb;
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
/*     */   public String getCommandClass() {
/*  67 */     return this.className;
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
/*     */   public Object getCommandObject(DataHandler dh, ClassLoader loader) throws IOException, ClassNotFoundException {
/* 111 */     Object new_bean = null;
/*     */ 
/*     */     
/* 114 */     new_bean = Beans.instantiate(loader, this.className);
/*     */ 
/*     */     
/* 117 */     if (new_bean != null) {
/* 118 */       if (new_bean instanceof CommandObject) {
/* 119 */         ((CommandObject)new_bean).setCommandContext(this.verb, dh);
/* 120 */       } else if (new_bean instanceof Externalizable && 
/* 121 */         dh != null) {
/* 122 */         InputStream is = dh.getInputStream();
/* 123 */         if (is != null) {
/* 124 */           ((Externalizable)new_bean).readExternal(new ObjectInputStream(is));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 131 */     return new_bean;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class Beans
/*     */   {
/*     */     static final Method instantiateMethod;
/*     */ 
/*     */     
/*     */     static {
/*     */       Method m;
/*     */       try {
/* 144 */         Class<?> c = Class.forName("java.beans.Beans");
/* 145 */         m = c.getDeclaredMethod("instantiate", new Class[] { ClassLoader.class, String.class });
/* 146 */       } catch (ClassNotFoundException e) {
/* 147 */         m = null;
/* 148 */       } catch (NoSuchMethodException e) {
/* 149 */         m = null;
/*     */       } 
/* 151 */       instantiateMethod = m;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static Object instantiate(ClassLoader loader, String cn) throws IOException, ClassNotFoundException {
/* 162 */       if (instantiateMethod != null) {
/*     */ 
/*     */         
/*     */         try {
/* 166 */           return (Object) instantiateMethod.invoke(null, new ClassLoader[] { loader });
/* 167 */         } catch (InvocationTargetException e) {
/* 168 */           Exception exception = e;
/* 169 */         } catch (IllegalAccessException e) {
/* 170 */           Exception exception = e;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 175 */         SecurityManager security = System.getSecurityManager();
/* 176 */         if (security != null) {
/*     */           
/* 178 */           String cname = cn.replace('/', '.');
/* 179 */           if (cname.startsWith("[")) {
/* 180 */             int b = cname.lastIndexOf('[') + 2;
/* 181 */             if (b > 1 && b < cname.length()) {
/* 182 */               cname = cname.substring(b);
/*     */             }
/*     */           } 
/* 185 */           int i = cname.lastIndexOf('.');
/* 186 */           if (i != -1) {
/* 187 */             security.checkPackageAccess(cname.substring(0, i));
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 192 */         if (loader == null)
/*     */         {
/* 194 */           loader = AccessController.<ClassLoader>doPrivileged(new PrivilegedAction<ClassLoader>() {
/*     */                 public ClassLoader run() {
/* 196 */                   ClassLoader cl = null;
/*     */                   try {
/* 198 */                     cl = ClassLoader.getSystemClassLoader();
/* 199 */                   } catch (SecurityException securityException) {}
/* 200 */                   return cl;
/*     */                 }
/*     */               });
/*     */         }
/* 204 */         Class<?> beanClass = Class.forName(cn, true, loader);
/*     */         try {
/* 206 */           return (Object) beanClass.newInstance();
/* 207 */         } catch (Exception ex) {
/* 208 */           throw new ClassNotFoundException("" + beanClass + ": " + beanClass, ex);
/*     */         } 
/*     */       } 
/*     */       
/* 212 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   class Object implements PrivilegedAction {
/*     */     public ClassLoader run() {
/*     */       ClassLoader cl = null;
/*     */       try {
/*     */         cl = ClassLoader.getSystemClassLoader();
/*     */       } catch (SecurityException securityException) {}
/*     */       return cl;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\CommandInfo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */