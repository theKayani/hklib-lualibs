package com.hk.lua.jda;

import com.hk.lua.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import org.jetbrains.annotations.NotNull;

public class LuaMessage extends LuaUserdata
{
	private final Message message;

	public LuaMessage(Message message)
	{
		this.message = message;

		metatable = messageMetatable;
		metatable.rawSet("id", message.getIdLong());
	}

	private static LuaObject reply(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaMessage))
			throw Lua.badArgument(0, "reply", "expected MESSAGE*");
		if(args.length < 2)
			throw Lua.badArgument(1, "reply", "expected string");

		LuaMessage message = (LuaMessage) args[0];
		return new LuaMessage(message.message.reply(args[1].getString(interp)).complete());
	}

	private static LuaObject getContent(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaMessage))
			throw Lua.badArgument(0, "getContent", "expected MESSAGE*");

		LuaMessage message = (LuaMessage) args[0];
		return Lua.newString(message.message.getContentDisplay());
	}

	private static LuaObject getContentRaw(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaMessage))
			throw Lua.badArgument(0, "getContentRaw", "expected MESSAGE*");

		LuaMessage message = (LuaMessage) args[0];
		return Lua.newString(message.message.getContentRaw());
	}

	private static LuaObject getChannel(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaMessage))
			throw Lua.badArgument(0, "getChannel", "expected MESSAGE*");

		LuaMessage message = (LuaMessage) args[0];
		return new LuaMessageChannel(message.message.getChannel());
	}

	@Override
	@NotNull
	public String name()
	{
		return "MESSAGE*";
	}

	@Override
	public Message getUserdata()
	{
		return message;
	}

	private static final LuaObject messageMetatable;

	static
	{
		messageMetatable = Lua.newTable();
		messageMetatable.rawSet("__name", "MESSAGE*");
		messageMetatable.rawSet("__index", messageMetatable);

		messageMetatable.rawSet("reply", Lua.newMethod(LuaMessage::reply));
		messageMetatable.rawSet("getContent", Lua.newMethod(LuaMessage::getContent));
		messageMetatable.rawSet("getContentRaw", Lua.newMethod(LuaMessage::getContentRaw));
		messageMetatable.rawSet("getChannel", Lua.newMethod(LuaMessage::getChannel));
	}
}
