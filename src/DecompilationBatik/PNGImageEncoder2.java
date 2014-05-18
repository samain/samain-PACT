package DecompilationBatik;

import java.awt.Rectangle;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import org.apache.batik.ext.awt.image.codec.png.PNGEncodeParam;
import org.apache.batik.ext.awt.image.codec.util.ImageEncoderImpl;

// Referenced classes of package org.apache.batik.ext.awt.image.codec.png:
//            ChunkStream, IDATOutputStream, PNGEncodeParam

public class PNGImageEncoder2 extends ImageEncoderImpl
{

    private static final int PNG_COLOR_GRAY = 0;
    private static final int PNG_COLOR_RGB = 2;
    private static final int PNG_COLOR_PALETTE = 3;
    private static final int PNG_COLOR_GRAY_ALPHA = 4;
    private static final int PNG_COLOR_RGB_ALPHA = 6;
    private static final byte magic[] = {
        -119, 80, 78, 71, 13, 10, 26, 10
    };
    private PNGEncodeParam param;
    private RenderedImage image;
    private int width;
    private int height;
    private int bitDepth;
    private int bitShift;
    private int numBands;
    private int colorType;
    private int bpp;
    private boolean skipAlpha;
    private boolean compressGray;
    private boolean interlace;
    private byte redPalette[];
    private byte greenPalette[];
    private byte bluePalette[];
    private byte alphaPalette[];
    private DataOutputStream dataOutput;
    private byte prevRow[];
    private byte currRow[];
    private byte filteredRows[][];
    private static final float srgbChroma[] = {
        0.3127F, 0.329F, 0.64F, 0.33F, 0.3F, 0.6F, 0.15F, 0.06F
    };

    public PNGImageEncoder2(OutputStream output, PNGEncodeParam param)
    {
        super(output, param);
        skipAlpha = false;
        compressGray = false;
        redPalette = null;
        greenPalette = null;
        bluePalette = null;
        alphaPalette = null;
        prevRow = null;
        currRow = null;
        filteredRows = null;
        if(param != null)
        {
            this.param = param;
        }
        dataOutput = new DataOutputStream(output);
    }

    private void writeMagic()
        throws IOException
    {
        dataOutput.write(magic);
    }

    private void writeIHDR()
        throws IOException
    {
        ChunkStream2 cs = new ChunkStream2("IHDR");
        cs.writeInt(width);
        cs.writeInt(height);
        cs.writeByte((byte)bitDepth);
        cs.writeByte((byte)colorType);
        cs.writeByte(0);
        cs.writeByte(0);
        cs.writeByte(interlace ? 1 : 0);
        cs.writeToStream(dataOutput);
        cs.close();
    }

    private static int clamp(int val, int maxValue)
    {
        return val <= maxValue ? val : maxValue;
    }

    private void encodePass(OutputStream os, Raster ras, int xOffset, int yOffset, int xSkip, int ySkip)
        throws IOException
    {
    	long time1 = System.currentTimeMillis();
    	
        int minX = ras.getMinX();
        int minY = ras.getMinY();
        int width = ras.getWidth();
        int height = ras.getHeight();
        xOffset *= numBands;
        xSkip *= numBands;
        int samplesPerByte = 8 / bitDepth;
        int numSamples = width * numBands;
        int samples[] = new int[numSamples];
        int pixels = (((numSamples - xOffset) + xSkip) - 1) / xSkip;
        int bytesPerRow = pixels * numBands;
        
        long time2 = System.currentTimeMillis();
        
        if(bitDepth < 8)
        {
            bytesPerRow = ((bytesPerRow + samplesPerByte) - 1) / samplesPerByte;
        } else
        if(bitDepth == 16)
        {
            bytesPerRow *= 2;
        }
        if(bytesPerRow == 0)
        {
            return;
        }
        currRow = new byte[bytesPerRow + bpp];
        prevRow = new byte[bytesPerRow + bpp];
        filteredRows = new byte[5][bytesPerRow + bpp];
        int maxValue = (1 << bitDepth) - 1;
        
        long time3 = System.currentTimeMillis();
        
        for(int row = minY + yOffset; row < minY + height; row += ySkip)
        {
            ras.getPixels(minX, row, width, 1, samples);
            if(compressGray)
            {
                int shift = 8 - bitDepth;
                for(int i = 0; i < width; i++)
                {
                    samples[i] >>= shift;
                }

            }
            int count = bpp;
            int pos = 0;
            int tmp = 0;
            switch(bitDepth)
            {
            default:
                break;

            case 1: // '\001'
            case 2: // '\002'
            case 4: // '\004'
                int mask = samplesPerByte - 1;
                for(int s = xOffset; s < numSamples; s += xSkip)
                {
                    int val = clamp(samples[s] >> bitShift, maxValue);
                    tmp = tmp << bitDepth | val;
                    if(pos++ == mask)
                    {
                        currRow[count++] = (byte)tmp;
                        tmp = 0;
                        pos = 0;
                    }
                }

                if(pos != 0)
                {
                    tmp <<= (samplesPerByte - pos) * bitDepth;
                    currRow[count++] = (byte)tmp;
                }
                break;

            case 8: // '\b'
                for(int s = xOffset; s < numSamples; s += xSkip)
                {
                    for(int b = 0; b < numBands; b++)
                    {
                        currRow[count++] = (byte)clamp(samples[s + b] >> bitShift, maxValue);
                    }

                }

                break;

            case 16: // '\020'
                for(int s = xOffset; s < numSamples; s += xSkip)
                {
                    for(int b = 0; b < numBands; b++)
                    {
                        int val = clamp(samples[s + b] >> bitShift, maxValue);
                        currRow[count++] = (byte)(val >> 8);
                        currRow[count++] = (byte)(val & 0xff);
                    }

                }

                break;
            }
            long time4 = System.currentTimeMillis();
            int filterType = param.filterRow(currRow, prevRow, filteredRows, bytesPerRow, bpp);
            os.write(filterType);
            os.write(filteredRows[filterType], bpp, bytesPerRow);
            byte swap[] = currRow;
            currRow = prevRow;
            prevRow = swap;
            long time5 = System.currentTimeMillis();
            System.out.println("PNGImageEncoder2 : encodePass : écriture OutputStream : " + (time5 - time4));
        }
        
        long time6 = System.currentTimeMillis();

        System.out.println("PNGImageEncoder2 : encodePass : définitions : " + (time2 - time1));
        System.out.println("PNGImageEncoder2 : encodePass : if + definitions : " + (time3 - time2));
        System.out.println("PNGImageEncoder2 : encodePass : boucle for : " + (time6 - time3));
    }

    private void writeIDAT()
        throws IOException
    {
    	long time1 = System.currentTimeMillis();
        IDATOutputStream2 ios = new IDATOutputStream2(dataOutput, 8192);
        DeflaterOutputStream dos = new DeflaterOutputStream(ios, new Deflater(9));
        Raster ras = image.getData(new Rectangle(image.getMinX(), image.getMinY(), image.getWidth(), image.getHeight()));
        long time2 = System.currentTimeMillis();
        if(skipAlpha)
        {
            int numBands = ras.getNumBands() - 1;
            int bandList[] = new int[numBands];
            for(int i = 0; i < numBands; i++)
            {
                bandList[i] = i;
            }

            ras = ras.createChild(0, 0, ras.getWidth(), ras.getHeight(), 0, 0, bandList);
        }
        long time3 = System.currentTimeMillis();
        if(interlace)
        {
        	long time5 = System.currentTimeMillis();
            encodePass(dos, ras, 0, 0, 8, 8);
            long time6 = System.currentTimeMillis();
            encodePass(dos, ras, 4, 0, 8, 8);
            long time7 = System.currentTimeMillis();
            encodePass(dos, ras, 0, 4, 4, 8);
            long time8 = System.currentTimeMillis();
            encodePass(dos, ras, 2, 0, 4, 4);
            long time9 = System.currentTimeMillis();
            encodePass(dos, ras, 0, 2, 2, 4);
            long time10 = System.currentTimeMillis();
            encodePass(dos, ras, 1, 0, 2, 2);
            long time11 = System.currentTimeMillis();
            encodePass(dos, ras, 0, 1, 1, 2);
            long time12 = System.currentTimeMillis();
            System.out.println("PNGImageEncoder2 : writeIDAT : interlace : encodePasss : " + (time6 - time5));
            System.out.println("PNGImageEncoder2 : writeIDAT : interlace : encodePasss : " + (time7 - time6));
            System.out.println("PNGImageEncoder2 : writeIDAT : interlace : encodePasss : " + (time8 - time7));
            System.out.println("PNGImageEncoder2 : writeIDAT : interlace : encodePasss : " + (time9 - time8));
            System.out.println("PNGImageEncoder2 : writeIDAT : interlace : encodePasss : " + (time10 - time9));
            System.out.println("PNGImageEncoder2 : writeIDAT : interlace : encodePasss : " + (time11 - time10));
            System.out.println("PNGImageEncoder2 : writeIDAT : interlace : encodePasss : " + (time12 - time11));
        } else
        {
        	long time13 = System.currentTimeMillis();
            encodePass(dos, ras, 0, 0, 1, 1);
            long time14 = System.currentTimeMillis();
            System.out.println("PNGImageEncoder2 : writeIDAT : interlace : encodePasss : else " + (time14 - time13));
            
        }
        dos.finish();
        ios.flush();
        long time4 = System.currentTimeMillis();
        System.out.println("PNGImageEncoder2 : writeIDAT : chargement classes : " + (time2 - time1));
        System.out.println("PNGImageEncoder2 : writeIDAT : skipAlpha : " + (time3 - time2));
        System.out.println("PNGImageEncoder2 : writeIDAT : interlace : " + (time4 - time3));
    }

    private void writeIEND()
        throws IOException
    {
        ChunkStream2 cs = new ChunkStream2("IEND");
        cs.writeToStream(dataOutput);
        cs.close();
    }

    private void writeCHRM()
        throws IOException
    {
        if(param.isChromaticitySet() || param.isSRGBIntentSet())
        {
            ChunkStream2 cs = new ChunkStream2("cHRM");
            float chroma[];
            if(!param.isSRGBIntentSet())
            {
                chroma = param.getChromaticity();
            } else
            {
                chroma = srgbChroma;
            }
            for(int i = 0; i < 8; i++)
            {
                cs.writeInt((int)(chroma[i] * 100000F));
            }

            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writeGAMA()
        throws IOException
    {
        if(param.isGammaSet() || param.isSRGBIntentSet())
        {
            ChunkStream2 cs = new ChunkStream2("gAMA");
            float gamma;
            if(!param.isSRGBIntentSet())
            {
                gamma = param.getGamma();
            } else
            {
                gamma = 0.4545454F;
            }
            cs.writeInt((int)(gamma * 100000F));
            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writeICCP()
        throws IOException
    {
        if(param.isICCProfileDataSet())
        {
            ChunkStream2 cs = new ChunkStream2("iCCP");
            byte ICCProfileData[] = param.getICCProfileData();
            cs.write(ICCProfileData);
            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writeSBIT()
        throws IOException
    {
        if(param.isSignificantBitsSet())
        {
            ChunkStream2 cs = new ChunkStream2("sBIT");
            int significantBits[] = param.getSignificantBits();
            int len = significantBits.length;
            for(int i = 0; i < len; i++)
            {
                cs.writeByte(significantBits[i]);
            }

            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writeSRGB()
        throws IOException
    {
        if(param.isSRGBIntentSet())
        {
            ChunkStream2 cs = new ChunkStream2("sRGB");
            int intent = param.getSRGBIntent();
            cs.write(intent);
            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writePLTE()
        throws IOException
    {
        if(redPalette == null)
        {
            return;
        }
        ChunkStream2 cs = new ChunkStream2("PLTE");
        for(int i = 0; i < redPalette.length; i++)
        {
            cs.writeByte(redPalette[i]);
            cs.writeByte(greenPalette[i]);
            cs.writeByte(bluePalette[i]);
        }

        cs.writeToStream(dataOutput);
        cs.close();
    }

    private void writeBKGD()
        throws IOException
    {
        if(param.isBackgroundSet())
        {
            ChunkStream2 cs = new ChunkStream2("bKGD");
            switch(colorType)
            {
            case 0: // '\0'
            case 4: // '\004'
                int gray = ((PNGEncodeParam.Gray)param).getBackgroundGray();
                cs.writeShort(gray);
                break;

            case 3: // '\003'
                int index = ((PNGEncodeParam.Palette)param).getBackgroundPaletteIndex();
                cs.writeByte(index);
                break;

            case 2: // '\002'
            case 6: // '\006'
                int rgb[] = ((PNGEncodeParam.RGB)param).getBackgroundRGB();
                cs.writeShort(rgb[0]);
                cs.writeShort(rgb[1]);
                cs.writeShort(rgb[2]);
                break;
            }
            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writeHIST()
        throws IOException
    {
        if(param.isPaletteHistogramSet())
        {
            ChunkStream2 cs = new ChunkStream2("hIST");
            int hist[] = param.getPaletteHistogram();
            for(int i = 0; i < hist.length; i++)
            {
                cs.writeShort(hist[i]);
            }

            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writeTRNS()
        throws IOException
    {
        if(param.isTransparencySet() && colorType != 4 && colorType != 6)
        {
            ChunkStream2 cs = new ChunkStream2("tRNS");
            if(param instanceof PNGEncodeParam.Palette)
            {
                byte t[] = ((PNGEncodeParam.Palette)param).getPaletteTransparency();
                for(int i = 0; i < t.length; i++)
                {
                    cs.writeByte(t[i]);
                }

            } else
            if(param instanceof PNGEncodeParam.Gray)
            {
                int t = ((PNGEncodeParam.Gray)param).getTransparentGray();
                cs.writeShort(t);
            } else
            if(param instanceof PNGEncodeParam.RGB)
            {
                int t[] = ((PNGEncodeParam.RGB)param).getTransparentRGB();
                cs.writeShort(t[0]);
                cs.writeShort(t[1]);
                cs.writeShort(t[2]);
            }
            cs.writeToStream(dataOutput);
            cs.close();
        } else
        if(colorType == 3)
        {
            int lastEntry = Math.min(255, alphaPalette.length - 1);
            int nonOpaque;
            for(nonOpaque = lastEntry; nonOpaque >= 0; nonOpaque--)
            {
                if(alphaPalette[nonOpaque] != -1)
                {
                    break;
                }
            }

            if(nonOpaque >= 0)
            {
                ChunkStream2 cs = new ChunkStream2("tRNS");
                for(int i = 0; i <= nonOpaque; i++)
                {
                    cs.writeByte(alphaPalette[i]);
                }

                cs.writeToStream(dataOutput);
                cs.close();
            }
        }
    }

    private void writePHYS()
        throws IOException
    {
        if(param.isPhysicalDimensionSet())
        {
            ChunkStream2 cs = new ChunkStream2("pHYs");
            int dims[] = param.getPhysicalDimension();
            cs.writeInt(dims[0]);
            cs.writeInt(dims[1]);
            cs.writeByte((byte)dims[2]);
            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writeSPLT()
        throws IOException
    {
        if(param.isSuggestedPaletteSet())
        {
            ChunkStream2 cs = new ChunkStream2("sPLT");
            System.out.println("sPLT not supported yet.");
            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writeTIME()
        throws IOException
    {
        if(param.isModificationTimeSet())
        {
            ChunkStream2 cs = new ChunkStream2("tIME");
            java.util.Date date = param.getModificationTime();
            TimeZone gmt = TimeZone.getTimeZone("GMT");
            GregorianCalendar cal = new GregorianCalendar(gmt);
            cal.setTime(date);
            int year = cal.get(1);
            int month = cal.get(2);
            int day = cal.get(5);
            int hour = cal.get(11);
            int minute = cal.get(12);
            int second = cal.get(13);
            cs.writeShort(year);
            cs.writeByte(month + 1);
            cs.writeByte(day);
            cs.writeByte(hour);
            cs.writeByte(minute);
            cs.writeByte(second);
            cs.writeToStream(dataOutput);
            cs.close();
        }
    }

    private void writeTEXT()
        throws IOException
    {
        if(param.isTextSet())
        {
            String text[] = param.getText();
            for(int i = 0; i < text.length / 2; i++)
            {
                byte keyword[] = text[2 * i].getBytes();
                byte value[] = text[2 * i + 1].getBytes();
                ChunkStream2 cs = new ChunkStream2("tEXt");
                cs.write(keyword, 0, Math.min(keyword.length, 79));
                cs.write(0);
                cs.write(value);
                cs.writeToStream(dataOutput);
                cs.close();
            }

        }
    }

    private void writeZTXT()
        throws IOException
    {
        if(param.isCompressedTextSet())
        {
            String text[] = param.getCompressedText();
            for(int i = 0; i < text.length / 2; i++)
            {
                byte keyword[] = text[2 * i].getBytes();
                byte value[] = text[2 * i + 1].getBytes();
                ChunkStream2 cs = new ChunkStream2("zTXt");
                cs.write(keyword, 0, Math.min(keyword.length, 79));
                cs.write(0);
                cs.write(0);
                DeflaterOutputStream dos = new DeflaterOutputStream(cs);
                dos.write(value);
                dos.finish();
                cs.writeToStream(dataOutput);
                cs.close();
            }

        }
    }

    private void writePrivateChunks()
        throws IOException
    {
        int numChunks = param.getNumPrivateChunks();
        for(int i = 0; i < numChunks; i++)
        {
            String type = param.getPrivateChunkType(i);
            byte data[] = param.getPrivateChunkData(i);
            ChunkStream2 cs = new ChunkStream2(type);
            cs.write(data);
            cs.writeToStream(dataOutput);
            cs.close();
        }

    }

    private PNGEncodeParam.Gray createGrayParam(byte redPalette[], byte greenPalette[], byte bluePalette[], byte alphaPalette[])
    {
        PNGEncodeParam.Gray param = new PNGEncodeParam.Gray();
        int numTransparent = 0;
        int grayFactor = 255 / ((1 << bitDepth) - 1);
        int entries = 1 << bitDepth;
        for(int i = 0; i < entries; i++)
        {
            byte red = redPalette[i];
            if(red != i * grayFactor || red != greenPalette[i] || red != bluePalette[i])
            {
                return null;
            }
            byte alpha = alphaPalette[i];
            if(alpha == 0)
            {
                param.setTransparentGray(i);
                if(++numTransparent > 1)
                {
                    return null;
                }
            } else
            if(alpha != -1)
            {
                return null;
            }
        }

        return param;
    }

    public void encode(RenderedImage im)
        throws IOException
    {
        image = im;
        width = image.getWidth();
        height = image.getHeight();
        SampleModel sampleModel = image.getSampleModel();
        int sampleSize[] = sampleModel.getSampleSize();
        bitDepth = -1;
        bitShift = 0;
        if(param instanceof PNGEncodeParam.Gray)
        {
            PNGEncodeParam.Gray paramg = (PNGEncodeParam.Gray)param;
            if(paramg.isBitDepthSet())
            {
                bitDepth = paramg.getBitDepth();
            }
            if(paramg.isBitShiftSet())
            {
                bitShift = paramg.getBitShift();
            }
        }
        if(bitDepth == -1)
        {
            bitDepth = sampleSize[0];
            for(int i = 1; i < sampleSize.length; i++)
            {
                if(sampleSize[i] != bitDepth)
                {
                    throw new RuntimeException();
                }
            }

            if(bitDepth > 2 && bitDepth < 4)
            {
                bitDepth = 4;
            } else
            if(bitDepth > 4 && bitDepth < 8)
            {
                bitDepth = 8;
            } else
            if(bitDepth > 8 && bitDepth < 16)
            {
                bitDepth = 16;
            } else
            if(bitDepth > 16)
            {
                throw new RuntimeException();
            }
        }
        numBands = sampleModel.getNumBands();
        
        System.out.println("PNGImageEncoder2 : encode : numBands : " + numBands);
        
        bpp = numBands * (bitDepth != 16 ? 1 : 2);
        java.awt.image.ColorModel colorModel = image.getColorModel();
        if(colorModel instanceof IndexColorModel)
        {
            if(bitDepth < 1 || bitDepth > 8)
            {
                throw new RuntimeException();
            }
            if(sampleModel.getNumBands() != 1)
            {
                throw new RuntimeException();
            }
            IndexColorModel icm = (IndexColorModel)colorModel;
            int size = icm.getMapSize();
            redPalette = new byte[size];
            greenPalette = new byte[size];
            bluePalette = new byte[size];
            alphaPalette = new byte[size];
            icm.getReds(redPalette);
            icm.getGreens(greenPalette);
            icm.getBlues(bluePalette);
            icm.getAlphas(alphaPalette);
            bpp = 1;
            if(param == null)
            {
                param = createGrayParam(redPalette, greenPalette, bluePalette, alphaPalette);
            }
            if(param == null)
            {
                param = new PNGEncodeParam.Palette();
            }
            if(param instanceof PNGEncodeParam.Palette)
            {
                PNGEncodeParam.Palette parami = (PNGEncodeParam.Palette)param;
                if(parami.isPaletteSet())
                {
                    int palette[] = parami.getPalette();
                    size = palette.length / 3;
                    int index = 0;
                    for(int i = 0; i < size; i++)
                    {
                        redPalette[i] = (byte)palette[index++];
                        greenPalette[i] = (byte)palette[index++];
                        bluePalette[i] = (byte)palette[index++];
                        alphaPalette[i] = -1;
                    }

                }
                colorType = 3;
            } else
            if(param instanceof PNGEncodeParam.Gray)
            {
                redPalette = greenPalette = bluePalette = alphaPalette = null;
                colorType = 0;
            } else
            {
                throw new RuntimeException();
            }
        } else
        if(numBands == 1)
        {
            if(param == null)
            {
                param = new PNGEncodeParam.Gray();
            }
            colorType = 0;
        } else
        if(numBands == 2)
        {
            if(param == null)
            {
                param = new PNGEncodeParam.Gray();
            }
            if(param.isTransparencySet())
            {
                skipAlpha = true;
                numBands = 1;
                if(sampleSize[0] == 8 && bitDepth < 8)
                {
                    compressGray = true;
                }
                bpp = bitDepth != 16 ? 1 : 2;
                colorType = 0;
            } else
            {
                if(bitDepth < 8)
                {
                    bitDepth = 8;
                }
                colorType = 4;
            }
        } else
        if(numBands == 3)
        {
            if(param == null)
            {
                param = new PNGEncodeParam.RGB();
            }
            colorType = 2;
        } else
        if(numBands == 4)
        {
            if(param == null)
            {
                param = new PNGEncodeParam.RGB();
            }
            if(param.isTransparencySet())
            {
                skipAlpha = true;
                numBands = 3;
                bpp = bitDepth != 16 ? 3 : 6;
                colorType = 2;
            } else
            {
                colorType = 6;
            }
        }
        System.out.println("PNGImageEncoder2 : encode : bitDepth : " + bitDepth);
        interlace = param.getInterlacing();
        long time1 = System.currentTimeMillis();
        writeMagic();
        long time2 = System.currentTimeMillis();
        writeIHDR();
        long time3 = System.currentTimeMillis();
        writeCHRM();
        long time4 = System.currentTimeMillis();
        writeGAMA();
        long time5 = System.currentTimeMillis();
        writeICCP();
        long time6 = System.currentTimeMillis();
        writeSBIT();
        long time7 = System.currentTimeMillis();
        writeSRGB();
        long time8 = System.currentTimeMillis();
        writePLTE();
        long time9 = System.currentTimeMillis();
        writeHIST();
        long time10 = System.currentTimeMillis();
        writeTRNS();
        long time11 = System.currentTimeMillis();
        writeBKGD();
        long time12 = System.currentTimeMillis();
        writePHYS();
        long time13 = System.currentTimeMillis();
        writeSPLT();
        long time14 = System.currentTimeMillis();
        writeTIME();
        long time15 = System.currentTimeMillis();
        writeTEXT();
        long time16 = System.currentTimeMillis();
        writeZTXT();
        long time17 = System.currentTimeMillis();
        writePrivateChunks();
        long time18 = System.currentTimeMillis();
        writeIDAT();
        long time19 = System.currentTimeMillis();
        writeIEND();
        long time20 = System.currentTimeMillis();
        
        System.out.println("PNGImageEncoder2 : encode : writeMagic : " + (time2 - time1));
        System.out.println("PNGImageEncoder2 : encode : writeIHDR : " + (time3 - time2));
        System.out.println("PNGImageEncoder2 : encode : writeCHRM : " + (time4 - time3));
        System.out.println("PNGImageEncoder2 : encode : writeGAMA : " + (time5 - time4));
        System.out.println("PNGImageEncoder2 : encode : writeICCP : " + (time6 - time5));
        System.out.println("PNGImageEncoder2 : encode : writeSBIT : " + (time7 - time6));
        System.out.println("PNGImageEncoder2 : encode : writeSRGB : " + (time8 - time7));
        System.out.println("PNGImageEncoder2 : encode : writePLTE : " + (time9 - time8));
        System.out.println("PNGImageEncoder2 : encode : writeHIST : " + (time10 - time9));
        System.out.println("PNGImageEncoder2 : encode : writeTRNS : " + (time11 - time10));
        System.out.println("PNGImageEncoder2 : encode : writeBKGD : " + (time12 - time11));
        System.out.println("PNGImageEncoder2 : encode : writePHYS : " + (time13 - time12));
        System.out.println("PNGImageEncoder2 : encode : writeSPLT : " + (time14 - time13));
        System.out.println("PNGImageEncoder2 : encode : writeTIME : " + (time15 - time14));
        System.out.println("PNGImageEncoder2 : encode : writeTEXT : " + (time16 - time15));
        System.out.println("PNGImageEncoder2 : encode : writeZTXT : " + (time17 - time16));
        System.out.println("PNGImageEncoder2 : encode : writePrivateChunks : " + (time18 - time17));
        System.out.println("PNGImageEncoder2 : encode : writeIDAT : " + (time19 - time18));
        System.out.println("PNGImageEncoder2 : encode : writeIEND : " + (time20 - time19));
        dataOutput.flush();
    }

}
