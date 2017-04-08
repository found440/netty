package uptime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by 301 on 2017/4/8.
 */
public class UptimeClientHandler extends SimpleChannelInboundHandler<Object> {
    long startTime = -1;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        if(startTime < 0){
            startTime = System.currentTimeMillis();
        }
        println("Connected to:"+ ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception{
        //Discard Received data
    }


    void println(String msg) {
        if(startTime < 0) {
            System.err.format("[SERVER IS DOWN] %s%n", msg);
        }else {
            System.err.format("[UPTIME: %5ds] %s%n", (System.currentTimeMillis()- startTime)/ 100 , msg);
        }
    }
}
