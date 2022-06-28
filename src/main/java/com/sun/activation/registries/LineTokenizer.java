/*     */ package com.sun.activation.registries;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Vector;
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
/*     */ class LineTokenizer
/*     */ {
/*     */   private int currentPosition;
/*     */   private int maxPosition;
/*     */   private String str;
/* 212 */   private Vector stack = new Vector();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String singles = "=";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LineTokenizer(String str) {
/* 222 */     this.currentPosition = 0;
/* 223 */     this.str = str;
/* 224 */     this.maxPosition = str.length();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void skipWhiteSpace() {
/* 231 */     while (this.currentPosition < this.maxPosition && 
/* 232 */       Character.isWhitespace(this.str.charAt(this.currentPosition))) {
/* 233 */       this.currentPosition++;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMoreTokens() {
/* 244 */     if (this.stack.size() > 0)
/* 245 */       return true; 
/* 246 */     skipWhiteSpace();
/* 247 */     return (this.currentPosition < this.maxPosition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextToken() {
/* 258 */     int size = this.stack.size();
/* 259 */     if (size > 0) {
/* 260 */       String t = (String) this.stack.elementAt(size - 1);
/* 261 */       this.stack.removeElementAt(size - 1);
/* 262 */       return t;
/*     */     } 
/* 264 */     skipWhiteSpace();
/*     */     
/* 266 */     if (this.currentPosition >= this.maxPosition) {
/* 267 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/* 270 */     int start = this.currentPosition;
/* 271 */     char c = this.str.charAt(start);
/* 272 */     if (c == '"') {
/* 273 */       this.currentPosition++;
/* 274 */       boolean filter = false;
/* 275 */       while (this.currentPosition < this.maxPosition) {
/* 276 */         c = this.str.charAt(this.currentPosition++);
/* 277 */         if (c == '\\') {
/* 278 */           this.currentPosition++;
/* 279 */           filter = true; continue;
/* 280 */         }  if (c == '"') {
/*     */           String s;
/*     */           
/* 283 */           if (filter) {
/* 284 */             StringBuffer sb = new StringBuffer();
/* 285 */             for (int i = start + 1; i < this.currentPosition - 1; i++) {
/* 286 */               c = this.str.charAt(i);
/* 287 */               if (c != '\\')
/* 288 */                 sb.append(c); 
/*     */             } 
/* 290 */             s = sb.toString();
/*     */           } else {
/* 292 */             s = this.str.substring(start + 1, this.currentPosition - 1);
/* 293 */           }  return s;
/*     */         } 
/*     */       } 
/* 296 */     } else if ("=".indexOf(c) >= 0) {
/* 297 */       this.currentPosition++;
/*     */     } else {
/* 299 */       while (this.currentPosition < this.maxPosition && "="
/* 300 */         .indexOf(this.str.charAt(this.currentPosition)) < 0 && 
/* 301 */         !Character.isWhitespace(this.str.charAt(this.currentPosition))) {
/* 302 */         this.currentPosition++;
/*     */       }
/*     */     } 
/* 305 */     return this.str.substring(start, this.currentPosition);
/*     */   }
/*     */   
/*     */   public void pushToken(String token) {
/* 309 */     this.stack.addElement(token);
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\registries\LineTokenizer.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */