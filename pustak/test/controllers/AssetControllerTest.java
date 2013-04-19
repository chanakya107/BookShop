package controllers;

import org.junit.Test;


public class AssetControllerTest {
    @Test(expected = IllegalArgumentException.class)
    public void creating_assetController_of_null_webContext_throws_IllegalArgumentException() {
        AssetController.createAssetController(null);
    }

//    @Test
//    public void serve_will_return_requestHandlerResult() {
//        WebContext wc = mock(WebContext.class);
//        stub(wc.requestPath()).toReturn("");
//
//        stub(wc.sendFile("contents/")).toReturn(RequestHandlerResult.ok("ok"));
//
//        AssetController ac = AssetController.createAssetController(wc);
//        Assert.assertTrue(ac.serve() != null);
//    }
}
