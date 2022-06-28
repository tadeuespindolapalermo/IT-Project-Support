/*     */ package javax.activation;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
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
/*     */ class SecuritySupport
/*     */ {
/*     */   public static ClassLoader getContextClassLoader() {
/*  28 */     return 
/*  29 */       AccessController.<ClassLoader>doPrivileged(new PrivilegedAction<ClassLoader>() {
/*     */           public ClassLoader run() {
/*  31 */             ClassLoader cl = null;
/*     */             try {
/*  33 */               cl = Thread.currentThread().getContextClassLoader();
/*  34 */             } catch (SecurityException securityException) {}
/*  35 */             return cl;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static InputStream getResourceAsStream(final Class c, final String name) throws IOException {
/*     */     try {
/*  43 */       return 
/*  44 */         AccessController.<InputStream>doPrivileged(new PrivilegedExceptionAction<InputStream>() {
/*     */             public InputStream run() throws IOException {
/*  46 */               return c.getResourceAsStream(name);
/*     */             }
/*     */           });
/*  49 */     } catch (PrivilegedActionException e) {
/*  50 */       throw (IOException)e.getException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static URL[] getResources(final ClassLoader cl, final String name) {
/*  55 */     return 
/*  56 */       AccessController.<URL[]>doPrivileged(new PrivilegedAction() {
/*     */           public URL run() {
/*  58 */             URL[] ret = null;
/*     */             
/*  60 */             try { List<URL> v = new ArrayList();
/*  61 */               Enumeration<URL> e = cl.getResources(name);
/*  62 */               while (e != null && e.hasMoreElements()) {
/*  63 */                 URL url = e.nextElement();
/*  64 */                 if (url != null)
/*  65 */                   v.add(url); 
/*     */               } 
/*  67 */               if (v.size() > 0) {
/*  68 */                 ret = new URL[v.size()];
/*  69 */                 ret = v.<URL>toArray(ret);
/*     */               }  }
/*  71 */             catch (IOException iOException) {  }
/*  72 */             catch (SecurityException securityException) {}
/*  73 */             return ret[0];
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static URL[] getSystemResources(final String name) {
/*  79 */     return 
/*  80 */       AccessController.<URL[]>doPrivileged(new PrivilegedAction() {
/*     */           public URL run() {
/*  82 */             URL[] ret = null;
/*     */             
/*  84 */             try { List<URL> v = new ArrayList();
/*  85 */               Enumeration<URL> e = ClassLoader.getSystemResources(name);
/*  86 */               while (e != null && e.hasMoreElements()) {
/*  87 */                 URL url = e.nextElement();
/*  88 */                 if (url != null)
/*  89 */                   v.add(url); 
/*     */               } 
/*  91 */               if (v.size() > 0) {
/*  92 */                 ret = new URL[v.size()];
/*  93 */                 ret = v.<URL>toArray(ret);
/*     */               }  }
/*  95 */             catch (IOException iOException) {  }
/*  96 */             catch (SecurityException securityException) {}
/*  97 */             return ret[0];
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static InputStream openStream(final URL url) throws IOException {
/*     */     try {
/* 104 */       return 
/* 105 */         AccessController.<InputStream>doPrivileged(new PrivilegedExceptionAction<InputStream>() {
/*     */             public InputStream run() throws IOException {
/* 107 */               return url.openStream();
/*     */             }
/*     */           });
/* 110 */     } catch (PrivilegedActionException e) {
/* 111 */       throw (IOException)e.getException();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\SecuritySupport.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */