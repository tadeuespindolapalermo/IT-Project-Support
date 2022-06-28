/*     */ package javax.activation;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MimeType
/*     */   implements Externalizable
/*     */ {
/*     */   private String primaryType;
/*     */   private String subType;
/*     */   private MimeTypeParameterList parameters;
/*     */   private static final String TSPECIALS = "()<>@,;:/[]?=\\\"";
/*     */   
/*     */   public MimeType() {
/*  35 */     this.primaryType = "application";
/*  36 */     this.subType = "*";
/*  37 */     this.parameters = new MimeTypeParameterList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MimeType(String rawdata) throws MimeTypeParseException {
/*  47 */     parse(rawdata);
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
/*     */   public MimeType(String primary, String sub) throws MimeTypeParseException {
/*  61 */     if (isValidToken(primary)) {
/*  62 */       this.primaryType = primary.toLowerCase(Locale.ENGLISH);
/*     */     } else {
/*  64 */       throw new MimeTypeParseException("Primary type is invalid.");
/*     */     } 
/*     */ 
/*     */     
/*  68 */     if (isValidToken(sub)) {
/*  69 */       this.subType = sub.toLowerCase(Locale.ENGLISH);
/*     */     } else {
/*  71 */       throw new MimeTypeParseException("Sub type is invalid.");
/*     */     } 
/*     */     
/*  74 */     this.parameters = new MimeTypeParameterList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parse(String rawdata) throws MimeTypeParseException {
/*  81 */     int slashIndex = rawdata.indexOf('/');
/*  82 */     int semIndex = rawdata.indexOf(';');
/*  83 */     if (slashIndex < 0 && semIndex < 0)
/*     */     {
/*     */       
/*  86 */       throw new MimeTypeParseException("Unable to find a sub type."); } 
/*  87 */     if (slashIndex < 0 && semIndex >= 0)
/*     */     {
/*     */       
/*  90 */       throw new MimeTypeParseException("Unable to find a sub type."); } 
/*  91 */     if (slashIndex >= 0 && semIndex < 0) {
/*     */       
/*  93 */       this
/*  94 */         .primaryType = rawdata.substring(0, slashIndex).trim().toLowerCase(Locale.ENGLISH);
/*  95 */       this
/*  96 */         .subType = rawdata.substring(slashIndex + 1).trim().toLowerCase(Locale.ENGLISH);
/*  97 */       this.parameters = new MimeTypeParameterList();
/*  98 */     } else if (slashIndex < semIndex) {
/*     */       
/* 100 */       this
/* 101 */         .primaryType = rawdata.substring(0, slashIndex).trim().toLowerCase(Locale.ENGLISH);
/* 102 */       this
/* 103 */         .subType = rawdata.substring(slashIndex + 1, semIndex).trim().toLowerCase(Locale.ENGLISH);
/* 104 */       this.parameters = new MimeTypeParameterList(rawdata.substring(semIndex));
/*     */     }
/*     */     else {
/*     */       
/* 108 */       throw new MimeTypeParseException("Unable to find a sub type.");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (!isValidToken(this.primaryType)) {
/* 115 */       throw new MimeTypeParseException("Primary type is invalid.");
/*     */     }
/*     */     
/* 118 */     if (!isValidToken(this.subType)) {
/* 119 */       throw new MimeTypeParseException("Sub type is invalid.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrimaryType() {
/* 128 */     return this.primaryType;
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
/*     */   public void setPrimaryType(String primary) throws MimeTypeParseException {
/* 140 */     if (!isValidToken(this.primaryType))
/* 141 */       throw new MimeTypeParseException("Primary type is invalid."); 
/* 142 */     this.primaryType = primary.toLowerCase(Locale.ENGLISH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubType() {
/* 151 */     return this.subType;
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
/*     */   public void setSubType(String sub) throws MimeTypeParseException {
/* 163 */     if (!isValidToken(this.subType))
/* 164 */       throw new MimeTypeParseException("Sub type is invalid."); 
/* 165 */     this.subType = sub.toLowerCase(Locale.ENGLISH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MimeTypeParameterList getParameters() {
/* 174 */     return this.parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getParameter(String name) {
/* 185 */     return this.parameters.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(String name, String value) {
/* 196 */     this.parameters.set(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeParameter(String name) {
/* 205 */     this.parameters.remove(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 212 */     return getBaseType() + getBaseType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseType() {
/* 222 */     return this.primaryType + "/" + this.primaryType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean match(MimeType type) {
/* 233 */     return (this.primaryType.equals(type.getPrimaryType()) && (this.subType
/* 234 */       .equals("*") || type
/* 235 */       .getSubType().equals("*") || this.subType
/* 236 */       .equals(type.getSubType())));
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
/*     */   public boolean match(String rawdata) throws MimeTypeParseException {
/* 248 */     return match(new MimeType(rawdata));
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
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 261 */     out.writeUTF(toString());
/* 262 */     out.flush();
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
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*     */     try {
/* 279 */       parse(in.readUTF());
/* 280 */     } catch (MimeTypeParseException e) {
/* 281 */       throw new IOException(e.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isTokenChar(char c) {
/* 291 */     return (c > ' ' && c < '' && "()<>@,;:/[]?=\\\"".indexOf(c) < 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidToken(String s) {
/* 298 */     int len = s.length();
/* 299 */     if (len > 0) {
/* 300 */       for (int i = 0; i < len; i++) {
/* 301 */         char c = s.charAt(i);
/* 302 */         if (!isTokenChar(c)) {
/* 303 */           return false;
/*     */         }
/*     */       } 
/* 306 */       return true;
/*     */     } 
/* 308 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\MimeType.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */