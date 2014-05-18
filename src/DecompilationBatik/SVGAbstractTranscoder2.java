package DecompilationBatik;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.apache.batik.bridge.BaseScriptingEnvironment;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.BridgeException;
import org.apache.batik.bridge.DefaultScriptSecurity;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.NoLoadScriptSecurity;
import org.apache.batik.bridge.RelaxedScriptSecurity;
import org.apache.batik.bridge.SVGAnimationEngine;
import org.apache.batik.bridge.SVGUtilities;
import org.apache.batik.bridge.ScriptSecurity;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.bridge.ViewBox;
import org.apache.batik.bridge.svg12.SVG12BridgeContext;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.dom.util.DOMUtilities;
import org.apache.batik.dom.util.DocumentFactory;
import org.apache.batik.gvt.CanvasGraphicsNode;
import org.apache.batik.gvt.CompositeGraphicsNode;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.XMLAbstractTranscoder;
import org.apache.batik.transcoder.keys.BooleanKey;
import org.apache.batik.transcoder.keys.FloatKey;
import org.apache.batik.transcoder.keys.LengthKey;
import org.apache.batik.transcoder.keys.Rectangle2DKey;
import org.apache.batik.transcoder.keys.StringKey;
import org.apache.batik.util.ParsedURL;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.batik.transcoder.AbstractTranscoder;

// Referenced classes of package org.apache.batik.transcoder:
//            XMLAbstractTranscoder, TranscoderException, TranscoderSupport, TranscodingHints, 
//            TranscoderInput, TranscoderOutput, ErrorHandler

public abstract class SVGAbstractTranscoder2 extends XMLAbstractTranscoder
{
    protected class SVGAbstractTranscoderUserAgent extends UserAgentAdapter
    {

        protected java.util.List scripts;

        public AffineTransform getTransform()
        {
            return curTxf;
        }

        public void setTransform(AffineTransform at)
        {
            curTxf = at;
        }

        public Dimension2D getViewportSize()
        {
            return new Dimension((int)width, (int)height);
        }

        public void displayError(String message)
        {
            try
            {
                handler.error(new TranscoderException(message));
            }
            catch(TranscoderException ex)
            {
                throw new RuntimeException(ex.getMessage());
            }
        }

        public void displayError(Exception e)
        {
            try
            {
                e.printStackTrace();
                handler.error(new TranscoderException(e));
            }
            catch(TranscoderException ex)
            {
                throw new RuntimeException(ex.getMessage());
            }
        }

        public void displayMessage(String message)
        {
            try
            {
                handler.warning(new TranscoderException(message));
            }
            catch(TranscoderException ex)
            {
                throw new RuntimeException(ex.getMessage());
            }
        }

        public float getPixelUnitToMillimeter()
        {
            Object obj = hints.get(SVGAbstractTranscoder2.KEY_PIXEL_UNIT_TO_MILLIMETER);
            if(obj != null)
            {
                return ((Float)obj).floatValue();
            } else
            {
                return super.getPixelUnitToMillimeter();
            }
        }

        public String getLanguages()
        {
            if(hints.containsKey(SVGAbstractTranscoder2.KEY_LANGUAGE))
            {
                return (String)hints.get(SVGAbstractTranscoder2.KEY_LANGUAGE);
            } else
            {
                return super.getLanguages();
            }
        }

        public String getMedia()
        {
            String s = (String)hints.get(SVGAbstractTranscoder2.KEY_MEDIA);
            if(s != null)
            {
                return s;
            } else
            {
                return super.getMedia();
            }
        }

        public String getDefaultFontFamily()
        {
            String s = (String)hints.get(SVGAbstractTranscoder2.KEY_DEFAULT_FONT_FAMILY);
            if(s != null)
            {
                return s;
            } else
            {
                return super.getDefaultFontFamily();
            }
        }

        public String getAlternateStyleSheet()
        {
            String s = (String)hints.get(SVGAbstractTranscoder2.KEY_ALTERNATE_STYLESHEET);
            if(s != null)
            {
                return s;
            } else
            {
                return super.getAlternateStyleSheet();
            }
        }

        public String getUserStyleSheetURI()
        {
            String s = (String)hints.get(SVGAbstractTranscoder2.KEY_USER_STYLESHEET_URI);
            if(s != null)
            {
                return s;
            } else
            {
                return super.getUserStyleSheetURI();
            }
        }

        public String getXMLParserClassName()
        {
            String s = (String)hints.get(XMLAbstractTranscoder.KEY_XML_PARSER_CLASSNAME);
            if(s != null)
            {
                return s;
            } else
            {
                return super.getXMLParserClassName();
            }
        }

        public boolean isXMLParserValidating()
        {
            Boolean b = (Boolean)hints.get(XMLAbstractTranscoder.KEY_XML_PARSER_VALIDATING);
            if(b != null)
            {
                return b.booleanValue();
            } else
            {
                return super.isXMLParserValidating();
            }
        }

        public ScriptSecurity getScriptSecurity(String scriptType, ParsedURL scriptPURL, ParsedURL docPURL)
        {
            if(scripts == null)
            {
                computeAllowedScripts();
            }
            if(!scripts.contains(scriptType))
            {
                return new NoLoadScriptSecurity(scriptType);
            }
            boolean constrainOrigin = true;
            if(hints.containsKey(SVGAbstractTranscoder2.KEY_CONSTRAIN_SCRIPT_ORIGIN))
            {
                constrainOrigin = ((Boolean)hints.get(SVGAbstractTranscoder2.KEY_CONSTRAIN_SCRIPT_ORIGIN)).booleanValue();
            }
            if(constrainOrigin)
            {
                return new DefaultScriptSecurity(scriptType, scriptPURL, docPURL);
            } else
            {
                return new RelaxedScriptSecurity(scriptType, scriptPURL, docPURL);
            }
        }

        protected void computeAllowedScripts()
        {
            scripts = new LinkedList();
            if(!hints.containsKey(SVGAbstractTranscoder2.KEY_ALLOWED_SCRIPT_TYPES))
            {
                return;
            }
            String allowedScripts = (String)hints.get(SVGAbstractTranscoder2.KEY_ALLOWED_SCRIPT_TYPES);
            for(StringTokenizer st = new StringTokenizer(allowedScripts, ","); st.hasMoreTokens(); scripts.add(st.nextToken())) { }
        }

        public SVGAbstractTranscoderUserAgent()
        {
            addStdFeatures();
        }
    }


    public static final String DEFAULT_DEFAULT_FONT_FAMILY = "Arial, Helvetica, sans-serif";
    protected Rectangle2D curAOI;
    protected AffineTransform curTxf;
    protected GraphicsNode root;
    protected BridgeContext ctx;
    protected GVTBuilder builder;
    protected float width;
    protected float height;
    protected UserAgent userAgent;
    public static final TranscodingHints.Key KEY_WIDTH = new LengthKey();
    public static final TranscodingHints.Key KEY_HEIGHT = new LengthKey();
    public static final TranscodingHints.Key KEY_MAX_WIDTH = new LengthKey();
    public static final TranscodingHints.Key KEY_MAX_HEIGHT = new LengthKey();
    public static final TranscodingHints.Key KEY_AOI = new Rectangle2DKey();
    public static final TranscodingHints.Key KEY_LANGUAGE = new StringKey();
    public static final TranscodingHints.Key KEY_MEDIA = new StringKey();
    public static final TranscodingHints.Key KEY_DEFAULT_FONT_FAMILY = new StringKey();
    public static final TranscodingHints.Key KEY_ALTERNATE_STYLESHEET = new StringKey();
    public static final TranscodingHints.Key KEY_USER_STYLESHEET_URI = new StringKey();
    public static final TranscodingHints.Key KEY_PIXEL_UNIT_TO_MILLIMETER;
    /**
     * @deprecated Field KEY_PIXEL_TO_MM is deprecated
     */
    public static final TranscodingHints.Key KEY_PIXEL_TO_MM;
    public static final TranscodingHints.Key KEY_EXECUTE_ONLOAD = new BooleanKey();
    public static final TranscodingHints.Key KEY_SNAPSHOT_TIME = new FloatKey();
    public static final TranscodingHints.Key KEY_ALLOWED_SCRIPT_TYPES = new StringKey();
    public static final String DEFAULT_ALLOWED_SCRIPT_TYPES = "text/ecmascript, application/ecmascript, text/javascript, application/javascript" +
", application/java-archive"
;
    public static final TranscodingHints.Key KEY_CONSTRAIN_SCRIPT_ORIGIN = new BooleanKey();

    protected SVGAbstractTranscoder2()
    {
        width = 400F;
        height = 400F;
        userAgent = createUserAgent();
        super.hints.put(XMLAbstractTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, "http://www.w3.org/2000/svg");
        super.hints.put(XMLAbstractTranscoder.KEY_DOCUMENT_ELEMENT, "svg");
        super.hints.put(XMLAbstractTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
        super.hints.put(KEY_MEDIA, "screen");
        super.hints.put(KEY_DEFAULT_FONT_FAMILY, "Arial, Helvetica, sans-serif");
        super.hints.put(KEY_EXECUTE_ONLOAD, Boolean.FALSE);
        super.hints.put(KEY_ALLOWED_SCRIPT_TYPES, "text/ecmascript, application/ecmascript, text/javascript, application/javascript" +
", application/java-archive"
);
    }

    protected UserAgent createUserAgent()
    {
        return new SVGAbstractTranscoderUserAgent();
    }

    protected DocumentFactory createDocumentFactory(DOMImplementation domImpl, String parserClassname)
    {
        return new SAXSVGDocumentFactory(parserClassname);
    }

    public void transcode(TranscoderInput input, TranscoderOutput output)
        throws TranscoderException
    {
        super.transcode(input, output);
        if(ctx != null)
        {
            ctx.dispose();
        }
    }

    protected void transcode(Document document, String uri, TranscoderOutput output)
        throws TranscoderException
    {
    	
        if(document != null && !(document.getImplementation() instanceof SVGDOMImplementation))
        {
            DOMImplementation impl = (DOMImplementation)super.hints.get(XMLAbstractTranscoder.KEY_DOM_IMPLEMENTATION);
            document = DOMUtilities.deepCloneDocument(document, impl);
            if(uri != null)
            {
                ParsedURL url = new ParsedURL(uri);
                ((SVGOMDocument)document).setParsedURL(url);
            }
        }
        if(super.hints.containsKey(KEY_WIDTH))
        {
            width = ((Float)super.hints.get(KEY_WIDTH)).floatValue();
        }
        if(super.hints.containsKey(KEY_HEIGHT))
        {
            height = ((Float)super.hints.get(KEY_HEIGHT)).floatValue();
        }
        SVGOMDocument svgDoc = (SVGOMDocument)document;
        org.w3c.dom.svg.SVGSVGElement root = svgDoc.getRootElement();
        ctx = createBridgeContext(svgDoc);
        builder = new GVTBuilder2();
        
        
        boolean isDynamic = super.hints.containsKey(KEY_EXECUTE_ONLOAD) && ((Boolean)super.hints.get(KEY_EXECUTE_ONLOAD)).booleanValue();
        GraphicsNode gvtRoot;
        
        System.out.println("transcode :  isDynamic" + super.hints.containsKey(KEY_SNAPSHOT_TIME));
        
         try
        {
            if(isDynamic)
            {
                ctx.setDynamicState(2);
            }
            long time1 = System.currentTimeMillis();
            
            gvtRoot = builder.build(ctx, svgDoc);
            
            long time2 = System.currentTimeMillis();
            
            System.out.println("SVGAbstractTranscoder2 : transcode : CanvasGraphicsNode : " + (time2 - time1));
            
            if(ctx.isDynamic())
            {
                BaseScriptingEnvironment se = new BaseScriptingEnvironment(ctx);
                se.loadScripts();
                se.dispatchSVGLoadEvent();
                if(super.hints.containsKey(KEY_SNAPSHOT_TIME))
                {
                    float t = ((Float)super.hints.get(KEY_SNAPSHOT_TIME)).floatValue();
                    ctx.getAnimationEngine().setCurrentTime(t);
                } else
                if(ctx.isSVG12())
                {
                    float t = SVGUtilities.convertSnapshotTime(root, null);
                    ctx.getAnimationEngine().setCurrentTime(t);
                }
            }
        }
        catch(BridgeException ex)
        {
            ex.printStackTrace();
            throw new TranscoderException(ex);
        }
        
        
        
        
        float docWidth = (float)ctx.getDocumentSize().getWidth();
        float docHeight = (float)ctx.getDocumentSize().getHeight();
        setImageSize(docWidth, docHeight);
        AffineTransform Px;
        
       
        
        if(super.hints.containsKey(KEY_AOI))
        {
            Rectangle2D aoi = (Rectangle2D)super.hints.get(KEY_AOI);
            Px = new AffineTransform();
            double sx = (double)width / aoi.getWidth();
            double sy = (double)height / aoi.getHeight();
            double scale = Math.min(sx, sy);
            Px.scale(scale, scale);
            double tx = -aoi.getX() + ((double)width / scale - aoi.getWidth()) / 2D;
            double ty = -aoi.getY() + ((double)height / scale - aoi.getHeight()) / 2D;
            Px.translate(tx, ty);
            curAOI = aoi;
        } else
        {
            String ref = (new ParsedURL(uri)).getRef();
            String viewBox = root.getAttributeNS(null, "viewBox");
            if(ref != null && ref.length() != 0)
            {
                Px = ViewBox.getViewTransform(ref, root, width, height, ctx);
            } else
            if(viewBox != null && viewBox.length() != 0)
            {
                String aspectRatio = root.getAttributeNS(null, "preserveAspectRatio");
                Px = ViewBox.getPreserveAspectRatioTransform(root, viewBox, aspectRatio, width, height, ctx);
            } else
            {
                float xscale = width / docWidth;
                float yscale = height / docHeight;
                float scale = Math.min(xscale, yscale);
                Px = AffineTransform.getScaleInstance(scale, scale);
            }
            curAOI = new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, width, height);
        }
        
        
        
        CanvasGraphicsNode cgn = getCanvasGraphicsNode(gvtRoot);
        if(cgn != null)
        {
            cgn.setViewingTransform(Px);
            curTxf = new AffineTransform();
        } else
        {
            curTxf = Px;
        }
        this.root = gvtRoot;
        
       
    }

    protected CanvasGraphicsNode getCanvasGraphicsNode(GraphicsNode gn)
    {
    	
        if(!(gn instanceof CompositeGraphicsNode))
        {
            return null;
        }
        CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
        java.util.List children = cgn.getChildren();
        if(children.size() == 0)
        {
            return null;
        }
        gn = (GraphicsNode)children.get(0);
        if(!(gn instanceof CanvasGraphicsNode))
        {
            return null;
        } else
        {
            return (CanvasGraphicsNode)gn;
        }
    }

    protected BridgeContext createBridgeContext(SVGOMDocument doc)
    {
        return createBridgeContext(doc.isSVG12() ? "1.2" : "1.x");
    }

    protected BridgeContext createBridgeContext()
    {
        return createBridgeContext("1.x");
    }

    protected BridgeContext createBridgeContext(String svgVersion)
    {
        if("1.2".equals(svgVersion))
        {
            return new SVG12BridgeContext(userAgent);
        } else
        {
            return new BridgeContext(userAgent);
        }
    }

    protected void setImageSize(float docWidth, float docHeight)
    {
    	long time1 = System.currentTimeMillis();
    	
        float imgWidth = -1F;
        if(super.hints.containsKey(KEY_WIDTH))
        {
            imgWidth = ((Float)super.hints.get(KEY_WIDTH)).floatValue();
        }
        float imgHeight = -1F;
        if(super.hints.containsKey(KEY_HEIGHT))
        {
            imgHeight = ((Float)super.hints.get(KEY_HEIGHT)).floatValue();
        }
        if(imgWidth > 0.0F && imgHeight > 0.0F)
        {
            width = imgWidth;
            height = imgHeight;
        } else
        if(imgHeight > 0.0F)
        {
            width = (docWidth * imgHeight) / docHeight;
            height = imgHeight;
        } else
        if(imgWidth > 0.0F)
        {
            width = imgWidth;
            height = (docHeight * imgWidth) / docWidth;
        } else
        {
            width = docWidth;
            height = docHeight;
        }
        float imgMaxWidth = -1F;
        if(super.hints.containsKey(KEY_MAX_WIDTH))
        {
            imgMaxWidth = ((Float)super.hints.get(KEY_MAX_WIDTH)).floatValue();
        }
        float imgMaxHeight = -1F;
        if(super.hints.containsKey(KEY_MAX_HEIGHT))
        {
            imgMaxHeight = ((Float)super.hints.get(KEY_MAX_HEIGHT)).floatValue();
        }
        if(imgMaxHeight > 0.0F && height > imgMaxHeight)
        {
            width = (docWidth * imgMaxHeight) / docHeight;
            height = imgMaxHeight;
        }
        if(imgMaxWidth > 0.0F && width > imgMaxWidth)
        {
            width = imgMaxWidth;
            height = (docHeight * imgMaxWidth) / docWidth;
        }
        
        long time2 = System.currentTimeMillis();
        
        System.out.println("setImageSize : " + (time2-time1));
    }

    static 
    {
        KEY_PIXEL_UNIT_TO_MILLIMETER = new FloatKey();
        KEY_PIXEL_TO_MM = KEY_PIXEL_UNIT_TO_MILLIMETER;
    }
}
