package com.cintas.sdklibrary;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class PDFModule {

   public static void CreatePdf(Activity context , String content){
       PdfDocument document = new PdfDocument();
       PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
       PdfDocument.Page page = document.startPage(pageInfo);
       Canvas canvas = page.getCanvas();
       Paint paint = new Paint();
       paint.setColor(Color.RED);
       canvas.drawText(content, 80F, 50F, paint);
       document.finishPage(page);

       String path = Environment.getExternalStorageDirectory().getPath()+"/MyPdf/";
       File file = new File(path);
       if (!file.exists()){
           file.mkdirs();
       }
       String targetpath = file.getAbsolutePath()+"/test.pdf";
       File targetFile = new File(targetpath);

       try {
           document.writeTo(new FileOutputStream(targetFile));
           context.runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   Toast.makeText(context, "Pdf Success", Toast.LENGTH_SHORT).show();
               }
           });

       }catch (Exception e){
           context.runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   Toast.makeText(context, "Pdf failed >> "+e.getMessage(), Toast.LENGTH_SHORT).show();
               }
           });

           e.printStackTrace();
       }
       document.close();
   }


}
