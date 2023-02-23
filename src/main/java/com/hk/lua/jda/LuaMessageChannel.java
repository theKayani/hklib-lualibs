package com.hk.lua.jda;

import com.hk.lua.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LuaMessageChannel extends LuaUserdata
{
	private final MessageChannel channel;

	public LuaMessageChannel(MessageChannel channel)
	{
		this.channel = channel;

		metatable = channelMetatable;
		metatable.rawSet("id", channel.getIdLong());
	}

	private static LuaObject getName(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaMessageChannel))
			throw Lua.badArgument(0, "getName", "expected TEXTCHANNEL*");

		LuaMessageChannel channel = (LuaMessageChannel) args[0];
		return Lua.newString(channel.channel.getName());
	}

	private static LuaObject sendMessage(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaMessageChannel))
			throw Lua.badArgument(0, "sendMessage", "expected TEXTCHANNEL*");
		if(args.length < 2)
			throw Lua.badArgument(1, "sendMessage", "expected string");

		LuaMessageChannel channel = (LuaMessageChannel) args[0];
		return new LuaMessage(channel.channel.sendMessage(args[1].getString(interp)).complete());
	}

	@Override
	@NotNull
	public String name()
	{
		return "TEXTCHANNEL*";
	}

	@Override
	public MessageChannel getUserdata()
	{
		return channel;
	}

	private static final LuaObject channelMetatable;

	static
	{
		channelMetatable = Lua.newTable();
		channelMetatable.rawSet("__name", "TEXTCHANNEL*");
		channelMetatable.rawSet("__index", channelMetatable);

		channelMetatable.rawSet("sendMessage", Lua.newMethod(LuaMessageChannel::sendMessage));
		channelMetatable.rawSet("getName", Lua.newMethod(LuaMessageChannel::getName));
	}
}
