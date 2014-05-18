package DecompilationBatik;

import java.awt.image.BufferedImage;
import java.awt.image.SinglePixelPackedSampleModel;

import org.apache.batik.bridge.UserAgent;
import org.apache.batik.transcoder.*;
import org.apache.batik.transcoder.image.resources.Messages;
import org.apache.batik.transcoder.keys.FloatKey;
import org.apache.batik.transcoder.keys.IntegerKey;

// Referenced classes of package org.apache.batik.transcoder.image:
//            ImageTranscoder

public class PNGTranscoder2bis extends ImageTranscoder2
{
    public static interface WriteAdapter
    {

        public abstract void writeImage(PNGTranscoder2bis pngtranscoder, BufferedImage bufferedimage, TranscoderOutput transcoderoutput)
            throws TranscoderException;
    }


    public static final org.apache.batik.transcoder.TranscodingHints.Key KEY_GAMMA = new FloatKey();
    public static final float DEFAULT_CHROMA[] = {
        0.3127F, 0.329F, 0.64F, 0.33F, 0.3F, 0.6F, 0.15F, 0.06F
    };
    public static final org.apache.batik.transcoder.TranscodingHints.Key KEY_INDEXED = new IntegerKey();

    public PNGTranscoder2bis()
    {
        super.hints.put(ImageTranscoder2.KEY_FORCE_TRANSPARENT_WHITE, Boolean.FALSE);
    }

    public UserAgent getUserAgent()
    {
        return super.userAgent;
    }

    public BufferedImage createImage(int width, int height)
    {
        return new BufferedImage(width, height, 2);
    }

    private WriteAdapter getWriteAdapter(String className)
    {
        try
        {
            Class clazz = Class.forName(className);
            WriteAdapter adapter = (WriteAdapter)clazz.newInstance();
            return adapter;
        }
        catch(ClassNotFoundException e)
        {
            return null;
        }
        catch(InstantiationException e)
        {
            return null;
        }
        catch(IllegalAccessException e)
        {
            return null;
        }
    }

    public void writeImage(BufferedImage img, TranscoderOutput output)
        throws TranscoderException
    {
    	long time1 = System.currentTimeMillis();
        java.io.OutputStream ostream = output.getOutputStream();
        if(ostream == null)
        {
            throw new TranscoderException(Messages.formatMessage("png.badoutput", null));
        }
        boolean forceTransparentWhite = false;
        if(super.hints.containsKey(ImageTranscoder2.KEY_FORCE_TRANSPARENT_WHITE))
        {
            forceTransparentWhite = ((Boolean)super.hints.get(ImageTranscoder2.KEY_FORCE_TRANSPARENT_WHITE)).booleanValue();
        }
        if(forceTransparentWhite)
        {
            SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)img.getSampleModel();
            forceTransparentWhite(img, sppsm);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("PNGTranscoder2bis : writeImage : tests préliminaires" + (time2-time1));
       
        long time3 = System.currentTimeMillis();
        
      //  WriteAdapter adapter = getWriteAdapter("Synchronisation.PNGTranscoder2bis$WriteAdapter");
        
        // "org.apache.batik.ext.awt.image.codec.png.PNGTranscoderInternalCodecWriteAdapter"
        
        PNGTranscoderInternalCodecWriteAdapter2 adapter = new PNGTranscoderInternalCodecWriteAdapter2();
        
        long time4 = System.currentTimeMillis();
        
        System.out.println("PNGTranscoder2bis : writeImage : chargement du WriteAdapter : " + (time4-time3));
        
        if(adapter == null)
        {
          //  adapter = getWriteAdapter("org.apache.batik.transcoder.image.PNGTranscoderImageIOWriteAdapter");
        }
        if(adapter == null)
        {
            throw new TranscoderException("Could not write PNG file because no WriteAdapter is availble");
        } else
        {
            adapter.writeImage(this, img, output);
            return;
        }
    }

}
