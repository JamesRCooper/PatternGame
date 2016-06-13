package com.cooper.game.pool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.cooper.game.interactive.InteractiveFactory;

public class RoomBuilderTest {

    String roomSrc = "src/test/resources/arena/TOWN_TESTING.arena";

    @Test
    public void testRegex() {

        Pattern interactveMetaParser = Pattern.compile("-(.*?)\\((.*)\\)");

        String intereactiveMetaExample = "P-SIGN(Hello there, buddy))";
        Matcher matcher = interactveMetaParser.matcher(intereactiveMetaExample);
        Assert.assertEquals(2, matcher.groupCount());
    }

    @Test
    public void testBuilder() {


        RoomBuilder builder = new RoomBuilder(new InteractiveFactory());
        Room room = builder.buildRoomFromSource(roomSrc);

        Assert.assertEquals("TOWN_TESTING", room.getRoomName());
    }
}
