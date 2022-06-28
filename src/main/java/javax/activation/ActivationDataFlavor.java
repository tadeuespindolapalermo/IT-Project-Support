/*     */ package javax.activation;
/*     */ 
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActivationDataFlavor
/*     */   extends DataFlavor
/*     */ {
/*  40 */   private String mimeType = null;
/*  41 */   private MimeType mimeObject = null;
/*  42 */   private String humanPresentableName = null;
/*  43 */   private Class representationClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActivationDataFlavor(Class representationClass, String mimeType, String humanPresentableName) {
/*  64 */     super(mimeType, humanPresentableName);
/*     */ 
/*     */     
/*  67 */     this.mimeType = mimeType;
/*  68 */     this.humanPresentableName = humanPresentableName;
/*  69 */     this.representationClass = representationClass;
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
/*     */   public ActivationDataFlavor(Class<?> representationClass, String humanPresentableName) {
/*  91 */     super(representationClass, humanPresentableName);
/*  92 */     this.mimeType = super.getMimeType();
/*  93 */     this.representationClass = representationClass;
/*  94 */     this.humanPresentableName = humanPresentableName;
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
/*     */   public ActivationDataFlavor(String mimeType, String humanPresentableName) {
/* 113 */     super(mimeType, humanPresentableName);
/* 114 */     this.mimeType = mimeType;
/*     */     try {
/* 116 */       this.representationClass = Class.forName("java.io.InputStream");
/* 117 */     } catch (ClassNotFoundException classNotFoundException) {}
/*     */ 
/*     */     
/* 120 */     this.humanPresentableName = humanPresentableName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMimeType() {
/* 129 */     return this.mimeType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class getRepresentationClass() {
/* 138 */     return this.representationClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHumanPresentableName() {
/* 147 */     return this.humanPresentableName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHumanPresentableName(String humanPresentableName) {
/* 156 */     this.humanPresentableName = humanPresentableName;
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
/*     */   public boolean equals(DataFlavor dataFlavor) {
/* 168 */     return (isMimeTypeEqual(dataFlavor) && dataFlavor
/* 169 */       .getRepresentationClass() == this.representationClass);
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
/*     */   public boolean isMimeTypeEqual(String mimeType) {
/* 185 */     MimeType mt = null;
/*     */     try {
/* 187 */       if (this.mimeObject == null)
/* 188 */         this.mimeObject = new MimeType(this.mimeType); 
/* 189 */       mt = new MimeType(mimeType);
/* 190 */     } catch (MimeTypeParseException e) {
/*     */       
/* 192 */       return this.mimeType.equalsIgnoreCase(mimeType);
/*     */     } 
/*     */     
/* 195 */     return this.mimeObject.match(mt);
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
/*     */   protected String normalizeMimeTypeParameter(String parameterName, String parameterValue) {
/* 215 */     return parameterValue;
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
/*     */   protected String normalizeMimeType(String mimeType) {
/* 231 */     return mimeType;
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\ActivationDataFlavor.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */