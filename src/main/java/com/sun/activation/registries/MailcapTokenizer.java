/*     */ package com.sun.activation.registries;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MailcapTokenizer
/*     */ {
/*     */   public static final int UNKNOWN_TOKEN = 0;
/*     */   public static final int START_TOKEN = 1;
/*     */   public static final int STRING_TOKEN = 2;
/*     */   public static final int EOI_TOKEN = 5;
/*     */   public static final int SLASH_TOKEN = 47;
/*     */   public static final int SEMICOLON_TOKEN = 59;
/*     */   public static final int EQUALS_TOKEN = 61;
/*     */   private String data;
/*     */   private int dataIndex;
/*     */   private int dataLength;
/*     */   private int currentToken;
/*     */   private String currentTokenValue;
/*     */   private boolean isAutoquoting;
/*     */   private char autoquoteChar;
/*     */   
/*     */   public MailcapTokenizer(String inputString) {
/*  33 */     this.data = inputString;
/*  34 */     this.dataIndex = 0;
/*  35 */     this.dataLength = inputString.length();
/*     */     
/*  37 */     this.currentToken = 1;
/*  38 */     this.currentTokenValue = "";
/*     */     
/*  40 */     this.isAutoquoting = false;
/*  41 */     this.autoquoteChar = ';';
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
/*     */   public void setIsAutoquoting(boolean value) {
/*  57 */     this.isAutoquoting = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentToken() {
/*  66 */     return this.currentToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String nameForToken(int token) {
/*  73 */     String name = "really unknown";
/*     */     
/*  75 */     switch (token) {
/*     */       case 0:
/*  77 */         name = "unknown";
/*     */         break;
/*     */       case 1:
/*  80 */         name = "start";
/*     */         break;
/*     */       case 2:
/*  83 */         name = "string";
/*     */         break;
/*     */       case 5:
/*  86 */         name = "EOI";
/*     */         break;
/*     */       case 47:
/*  89 */         name = "'/'";
/*     */         break;
/*     */       case 59:
/*  92 */         name = "';'";
/*     */         break;
/*     */       case 61:
/*  95 */         name = "'='";
/*     */         break;
/*     */     } 
/*     */     
/*  99 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCurrentTokenValue() {
/* 108 */     return this.currentTokenValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextToken() {
/* 117 */     if (this.dataIndex < this.dataLength) {
/*     */       
/* 119 */       while (this.dataIndex < this.dataLength && 
/* 120 */         isWhiteSpaceChar(this.data.charAt(this.dataIndex))) {
/* 121 */         this.dataIndex++;
/*     */       }
/*     */       
/* 124 */       if (this.dataIndex < this.dataLength) {
/*     */         
/* 126 */         char c = this.data.charAt(this.dataIndex);
/* 127 */         if (this.isAutoquoting) {
/* 128 */           if (c == ';' || c == '=') {
/* 129 */             this.currentToken = c;
/* 130 */             this.currentTokenValue = (new Character(c)).toString();
/* 131 */             this.dataIndex++;
/*     */           } else {
/* 133 */             processAutoquoteToken();
/*     */           }
/*     */         
/* 136 */         } else if (isStringTokenChar(c)) {
/* 137 */           processStringToken();
/* 138 */         } else if (c == '/' || c == ';' || c == '=') {
/* 139 */           this.currentToken = c;
/* 140 */           this.currentTokenValue = (new Character(c)).toString();
/* 141 */           this.dataIndex++;
/*     */         } else {
/* 143 */           this.currentToken = 0;
/* 144 */           this.currentTokenValue = (new Character(c)).toString();
/* 145 */           this.dataIndex++;
/*     */         } 
/*     */       } else {
/*     */         
/* 149 */         this.currentToken = 5;
/* 150 */         this.currentTokenValue = null;
/*     */       } 
/*     */     } else {
/* 153 */       this.currentToken = 5;
/* 154 */       this.currentTokenValue = null;
/*     */     } 
/*     */     
/* 157 */     return this.currentToken;
/*     */   }
/*     */ 
/*     */   
/*     */   private void processStringToken() {
/* 162 */     int initialIndex = this.dataIndex;
/*     */ 
/*     */     
/* 165 */     while (this.dataIndex < this.dataLength && 
/* 166 */       isStringTokenChar(this.data.charAt(this.dataIndex))) {
/* 167 */       this.dataIndex++;
/*     */     }
/*     */     
/* 170 */     this.currentToken = 2;
/* 171 */     this.currentTokenValue = this.data.substring(initialIndex, this.dataIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   private void processAutoquoteToken() {
/* 176 */     int initialIndex = this.dataIndex;
/*     */ 
/*     */ 
/*     */     
/* 180 */     boolean foundTerminator = false;
/* 181 */     while (this.dataIndex < this.dataLength && !foundTerminator) {
/* 182 */       char c = this.data.charAt(this.dataIndex);
/* 183 */       if (c != this.autoquoteChar) {
/* 184 */         this.dataIndex++; continue;
/*     */       } 
/* 186 */       foundTerminator = true;
/*     */     } 
/*     */ 
/*     */     
/* 190 */     this.currentToken = 2;
/* 191 */     this
/* 192 */       .currentTokenValue = fixEscapeSequences(this.data.substring(initialIndex, this.dataIndex));
/*     */   }
/*     */   
/*     */   private static boolean isSpecialChar(char c) {
/* 196 */     boolean lAnswer = false;
/*     */     
/* 198 */     switch (c) {
/*     */       case '"':
/*     */       case '(':
/*     */       case ')':
/*     */       case ',':
/*     */       case '/':
/*     */       case ':':
/*     */       case ';':
/*     */       case '<':
/*     */       case '=':
/*     */       case '>':
/*     */       case '?':
/*     */       case '@':
/*     */       case '[':
/*     */       case '\\':
/*     */       case ']':
/* 214 */         lAnswer = true;
/*     */         break;
/*     */     } 
/*     */     
/* 218 */     return lAnswer;
/*     */   }
/*     */   
/*     */   private static boolean isControlChar(char c) {
/* 222 */     return Character.isISOControl(c);
/*     */   }
/*     */   
/*     */   private static boolean isWhiteSpaceChar(char c) {
/* 226 */     return Character.isWhitespace(c);
/*     */   }
/*     */   
/*     */   private static boolean isStringTokenChar(char c) {
/* 230 */     return (!isSpecialChar(c) && !isControlChar(c) && !isWhiteSpaceChar(c));
/*     */   }
/*     */   
/*     */   private static String fixEscapeSequences(String inputString) {
/* 234 */     int inputLength = inputString.length();
/* 235 */     StringBuffer buffer = new StringBuffer();
/* 236 */     buffer.ensureCapacity(inputLength);
/*     */     
/* 238 */     for (int i = 0; i < inputLength; i++) {
/* 239 */       char currentChar = inputString.charAt(i);
/* 240 */       if (currentChar != '\\') {
/* 241 */         buffer.append(currentChar);
/*     */       }
/* 243 */       else if (i < inputLength - 1) {
/* 244 */         char nextChar = inputString.charAt(i + 1);
/* 245 */         buffer.append(nextChar);
/*     */ 
/*     */         
/* 248 */         i++;
/*     */       } else {
/* 250 */         buffer.append(currentChar);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 255 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\registries\MailcapTokenizer.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */