package DecompilationBatik;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.batik.bridge.UserAgent;
import org.apache.batik.ext.awt.image.codec.png.PNGEncodeParam;
import org.apache.batik.ext.awt.image.codec.png.PNGImageEncoder;
import org.apache.batik.ext.awt.image.rendered.IndexImage;
import org.apache.batik.transcoder.*;
import org.apache.batik.transcoder.image.PNGTranscoder;

// Referenced classes of package org.apache.batik.ext.awt.image.codec.png:
//            PNGImageEncoder, PNGEncodeParam

public class PNGTranscoderInternalCodecWriteAdapter2
  //  implements org.apache.batik.transcoder.image.PNGTranscoder.WriteAdapter
{

    public PNGTranscoderInternalCodecWriteAdapter2()
    {
    }

    public void writeImage(PNGTranscoder2bis transcoder, BufferedImage img, TranscoderOutput output)
        throws TranscoderException
    {
        TranscodingHints hints = transcoder.getTranscodingHints();
        int n = -1;
        if(hints.containsKey(PNGTranscoder.KEY_INDEXED))
        {
            n = ((Integer)hints.get(PNGTranscoder.KEY_INDEXED)).intValue();
            if(n == 1 || n == 2 || n == 4 || n == 8)
            {
                img = IndexImage.getIndexedImage(img, 1 << n);
            }
        }
        PNGEncodeParam params = PNGEncodeParam.getDefaultEncodeParam(img);
        if(params instanceof PNGEncodeParam.RGB)
        {
            ((PNGEncodeParam.RGB)params).setBackgroundRGB(new int[] {
                255, 255, 255
            });
        }
        if(hints.containsKey(PNGTranscoder.KEY_GAMMA))
        {
            float gamma = ((Float)hints.get(PNGTranscoder.KEY_GAMMA)).floatValue();
            if(gamma > 0.0F)
            {
                params.setGamma(gamma);
            }
            params.setChromaticity(PNGTranscoder.DEFAULT_CHROMA);
        } else
        {
            params.setSRGBIntent(0);
        }
        float PixSzMM = transcoder.getUserAgent().getPixelUnitToMillimeter();
        int numPix = (int)((double)(1000F / PixSzMM) + 0.5D);
        params.setPhysicalDimension(numPix, numPix, 1);
        try
        {
            OutputStream ostream = output.getOutputStream();
            PNGImageEncoder2 pngEncoder = new PNGImageEncoder2(ostream, params);
            long time1 = System.currentTimeMillis();
            pngEncoder.encode(img);
            long time2 = System.currentTimeMillis();
            ostream.flush();
            System.out.println("PNGTranscoderInternalCodecWriteAdapter2 : writeImage : pngEncoder : " + (time2 - time1));
        }
        catch(IOException ex)
        {
            throw new TranscoderException(ex);
        }
    }
}
