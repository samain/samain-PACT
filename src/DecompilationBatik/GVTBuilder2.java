package DecompilationBatik;

import java.util.List;

import org.apache.batik.bridge.Bridge;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.BridgeException;
import org.apache.batik.bridge.CSSUtilities;
import org.apache.batik.bridge.DocumentBridge;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.GenericBridge;
import org.apache.batik.bridge.GraphicsNodeBridge;
import org.apache.batik.bridge.InterruptedBridgeException;
import org.apache.batik.gvt.CompositeGraphicsNode;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.HaltingThread;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.*;

// Referenced classes of package org.apache.batik.bridge:
//            GraphicsNodeBridge, BridgeException, GenericBridge, InterruptedBridgeException, 
//            BridgeContext, DocumentBridge, CSSUtilities

public class GVTBuilder2 extends GVTBuilder
    implements SVGConstants
{

    public GVTBuilder2()
    {
    }

    public GraphicsNode build(BridgeContext ctx, Document document)
    {
        long time1 = System.currentTimeMillis();
        
        GraphicsNode gN = super.build(ctx, document);
        
        long time2 = System.currentTimeMillis();
        
        System.out.println("build : " + (time2 - time1));
        
        return gN;
    }

    public GraphicsNode build(BridgeContext ctx, Element e)
    {
    	
    	long time1 = System.currentTimeMillis();
    	 GraphicsNode gN = super.build(ctx, e);
         long time2 = System.currentTimeMillis();
         System.out.println("build2 : " + (time2 - time1));
         return gN;
    }

    

    protected void buildComposite(BridgeContext ctx, Element e, CompositeGraphicsNode parentNode)
    {
    	
    	 long time1 = System.currentTimeMillis();
    	
    	 super.buildComposite(ctx, e, parentNode);
    	 
    	 long time2 = System.currentTimeMillis();
        
        System.out.println("buildComposite : " + (time2 - time1));

    }

    protected void buildGraphicsNode(BridgeContext ctx, Element e, CompositeGraphicsNode parentNode)
    {
    	 long time1 = System.currentTimeMillis();
      	
    	 super.buildGraphicsNode(ctx, e, parentNode);
    	 
    	 long time2 = System.currentTimeMillis();
    	 
    	 System.out.println("buildGraphicsNode : " + (time2 - time1));
    	
    }

    protected void handleGenericBridges(BridgeContext ctx, Element e)
    {
        

    	 long time1 = System.currentTimeMillis();
     	
    	 super.handleGenericBridges(ctx, e);
    	 
    	 long time2 = System.currentTimeMillis();
    
    	 
    	 System.out.println("handleGenericBridges : " + (time2 - time1));
    	
    }
}
