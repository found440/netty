package discard;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by 301 on 2017/4/7.
 */
public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {
    private ByteBuf content;
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        System.out.println("connected");
        //Initialize the message
        content = ctx.alloc().directBuffer(DiscardClient.SIZE).writeZero(DiscardClient.SIZE);
        content.writeBytes(Unpooled.copiedBuffer("This msg from clien!", CharsetUtil.UTF_8));
        //Send the initial messages
        generateTraffic();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        content.release();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //Serveris supposed to send nothing, but if it send something, discard it.

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    long counter;
    private void generateTraffic() {
        //Flush the outbound buffer to the socket.
        //Once flushed, generate the same amout of traffic again.
        ctx.writeAndFlush(content.retainedDuplicate()).addListener(trafficGenerator);
    }

    private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {

        public void operationComplete(ChannelFuture future) {
            if(future.isSuccess()) {
                generateTraffic();
            }else {
                future.cause().printStackTrace();
                future.channel().close();
            }
        }
    };
}
