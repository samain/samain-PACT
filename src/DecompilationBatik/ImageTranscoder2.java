package DecompilationBatik;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

import org.apache.batik.ext.awt.image.GraphicsUtil;
import org.apache.batik.gvt.renderer.*;
import org.apache.batik.transcoder.*;
import org.apache.batik.transcoder.keys.BooleanKey;
import org.apache.batik.transcoder.keys.PaintKey;
import org.w3c.dom.Document;

public abstract class ImageTranscoder2 extends SVGAbstractTranscoder2
{

    public static final org.apache.batik.transcoder.TranscodingHints.Key KEY_BACKGROUND_COLOR = new PaintKey();
    public static final org.apache.batik.transcoder.TranscodingHints.Key KEY_FORCE_TRANSPARENT_WHITE = new BooleanKey();

    protected ImageTranscoder2()
    {
    }

    protected void transcode(Document document, String uri, TranscoderOutput output)
        throws TranscoderException
    {
    	long time1 = System.currentTimeMillis();
    	
        super.transcode(document, uri, output);
        
        long time2 = System.currentTimeMillis();
        
        System.out.println("ImageTranscoder2 : transcode : opérations GraphicsNode : " + (time2-time1));
        
        long time3 = System.currentTimeMillis();
        
        int w = (int)((double)super.width + 0.5D);
        int h = (int)((double)super.height + 0.5D);
        ImageRenderer renderer = createRenderer();
        renderer.updateOffScreen(w, h);
        renderer.setTransform(super.curTxf);
        renderer.setTree(super.root);
        super.root = null;
        try
        {
            java.awt.Shape raoi = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, super.width, super.height);
            renderer.repaint(super.curTxf.createInverse().createTransformedShape(raoi));
            BufferedImage rend = renderer.getOffScreen();
            renderer = null;
            BufferedImage dest = createImage(w, h);
            Graphics2D g2d = GraphicsUtil.createGraphics(dest);
            if(super.hints.containsKey(KEY_BACKGROUND_COLOR))
            {
                Paint bgcolor = (Paint)super.hints.get(KEY_BACKGROUND_COLOR);
                g2d.setComposite(AlphaComposite.SrcOver);
                g2d.setPaint(bgcolor);
                g2d.fillRect(0, 0, w, h);
            }
            if(rend != null)
            {
                g2d.drawRenderedImage(rend, new AffineTransform());
            }
            g2d.dispose();
            rend = null;
            writeImage(dest, output);
            
            long time4 = System.currentTimeMillis();
            
            System.out.println("ImageTranscoder2 : transcode : opérations écriture : " + (time4-time3));
        }
        catch(Exception ex)
        {
        	 long time4 = System.currentTimeMillis();
             
             System.out.println("ImageTranscoder2 : transcode : opérations écriture : " + (time4-time3));
        	
            throw new TranscoderException(ex);
        }
       
        
        
    }

    protected ImageRenderer createRenderer()
    {
        ImageRendererFactory rendFactory = new ConcreteImageRendererFactory();
        return rendFactory.createStaticImageRenderer();
    }

    protected void forceTransparentWhite(BufferedImage img, SinglePixelPackedSampleModel sppsm)
    {
        int w = img.getWidth();
        int h = img.getHeight();
        DataBufferInt biDB = (DataBufferInt)img.getRaster().getDataBuffer();
        int scanStride = sppsm.getScanlineStride();
        int dbOffset = biDB.getOffset();
        int pixels[] = biDB.getBankData()[0];
        int p = dbOffset;
        int adjust = scanStride - w;
        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        int pel = 0;
        for(int i = 0; i < h; i++)
        {
            for(int j = 0; j < w; j++)
            {
                pel = pixels[p];
                a = pel >> 24 & 0xff;
                r = pel >> 16 & 0xff;
                g = pel >> 8 & 0xff;
                b = pel & 0xff;
                r = (255 * (255 - a) + a * r) / 255;
                g = (255 * (255 - a) + a * g) / 255;
                b = (255 * (255 - a) + a * b) / 255;
                pixels[p++] = a << 24 & 0xff000000 | r << 16 & 0xff0000 | g << 8 & 0xff00 | b & 0xff;
            }

            p += adjust;
        }

    }

    public abstract BufferedImage createImage(int i, int j);

    public abstract void writeImage(BufferedImage bufferedimage, TranscoderOutput transcoderoutput)
        throws TranscoderException;

}
