package perfomeria.core.data.internal.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Objects;

@Root(strict = false, name = "rss")
public class Rss {

    @Element(required = false, name = "channel")
    private Channel channel;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rss rss = (Rss) o;
        return Objects.equals(channel, rss.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel);
    }


    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}