package DecompilationBatik;

import java.io.*;

//Referenced classes of package org.apache.batik.ext.awt.image.codec.png:
//         CRC

class IDATOutputStream2 extends FilterOutputStream
{

 private static final byte typeSignature[] = {
     73, 68, 65, 84
 };
 private int bytesWritten;
 private int segmentLength;
 byte buffer[];

 public IDATOutputStream2(OutputStream output, int segmentLength)
 {
     super(output);
     bytesWritten = 0;
     this.segmentLength = segmentLength;
     buffer = new byte[segmentLength];
 }

 public void close()
     throws IOException
 {
     flush();
 }

 private void writeInt(int x)
     throws IOException
 {
     super.out.write(x >> 24);
     super.out.write(x >> 16 & 0xff);
     super.out.write(x >> 8 & 0xff);
     super.out.write(x & 0xff);
 }

 public void flush()
     throws IOException
 {
     writeInt(bytesWritten);
     super.out.write(typeSignature);
     super.out.write(buffer, 0, bytesWritten);
     int crc = -1;
     crc = CRC2.updateCRC(crc, typeSignature, 0, 4);
     crc = CRC2.updateCRC(crc, buffer, 0, bytesWritten);
     writeInt(~crc);
     bytesWritten = 0;
 }

 public void write(byte b[])
     throws IOException
 {
     write(b, 0, b.length);
 }

 public void write(byte b[], int off, int len)
     throws IOException
 {
     while(len > 0) 
     {
         int bytes = Math.min(segmentLength - bytesWritten, len);
         System.arraycopy(b, off, buffer, bytesWritten, bytes);
         off += bytes;
         len -= bytes;
         bytesWritten += bytes;
         if(bytesWritten == segmentLength)
         {
             flush();
         }
     }
 }

 public void write(int b)
     throws IOException
 {
     buffer[bytesWritten++] = (byte)b;
     if(bytesWritten == segmentLength)
     {
         flush();
     }
 }

}
