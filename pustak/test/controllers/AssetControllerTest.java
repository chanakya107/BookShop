package controllers;

import org.junit.Assert;
import org.junit.Test;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class AssetControllerTest {
    @Test(expected = IllegalArgumentException.class)
    public void creating_assetcontroller_of_null_webcontext_throws_IllegalArgumentException() {
        AssetController.createAssetController(null);
    }

    @Test
    public void serve_will_return_requestHandlerresult() {
        WebContext wc = mock(WebContext.class);
        stub(wc.requestPath()).toReturn("");

        stub(wc.sendFile("pustak/contents/")).toReturn(RequestHandlerResult.ok("ok"));

        AssetController ac = AssetController.createAssetController(wc);
        Assert.assertTrue(ac.serve() != null);
    }
}
