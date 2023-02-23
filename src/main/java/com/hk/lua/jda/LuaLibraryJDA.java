package com.hk.lua.jda;

import com.hk.lua.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * This library contains various methods to help with the Java Discord API.
 */
@SuppressWarnings("unused")
public enum LuaLibraryJDA implements BiConsumer<Environment, LuaObject>, Lua.LuaMethod
{
	isConnected() {
		@Override
		public LuaObject call(LuaInterpreter interp, LuaObject[] args)
		{
			return Lua.newBool(isReady(interp));
		}
	},
	getTextChannel() {
		@Override
		public LuaObject call(LuaInterpreter interp, LuaObject[] args)
		{
			Lua.checkArgs(toString(), args, LuaType.INTEGER);
			checkReady(interp);

			long id = args[0].getLong();
			JDA jda = interp.getExtra(EXKEY_JDA, JDA.class);
			Objects.requireNonNull(jda);

			TextChannel channel = jda.getTextChannelById(id);

			if(channel == null)
				return Lua.NIL;
			return new LuaMessageChannel(channel);
		}
	},
	getUser() {
		@Override
		public LuaObject call(LuaInterpreter interp, LuaObject[] args)
		{
			Lua.checkArgs(toString(), args, LuaType.INTEGER);
			checkReady(interp);

			long id = args[0].getLong();
			JDA jda = interp.getExtra(EXKEY_JDA, JDA.class);
			Objects.requireNonNull(jda);

			User user = jda.getUserById(id);

			if(user == null)
				return Lua.NIL;
			return new LuaUser(user);
		}
	};

	private static void checkReady(LuaInterpreter interp)
	{
		if(!isReady(interp))
			throw new IllegalStateException("JDA instance required for Lua JDA library");
	}

	private static boolean isReady(LuaInterpreter interp)
	{
		JDA jda = interp.getExtra(EXKEY_JDA, JDA.class);
		return jda != null && jda.getStatus() == JDA.Status.CONNECTED;
	}

	/** {@inheritDoc} */
	@Override
	public LuaObject call(LuaInterpreter interp, LuaObject[] args)
	{
		throw new Error();
	}

	/** {@inheritDoc} */
	@Override
	public void accept(Environment env, LuaObject table)
	{
		checkReady(env.interp);

		String name = toString();
		if(name != null && !name.trim().isEmpty())
			table.rawSet(Lua.newString(name), Lua.newMethod(this));
	}

	public static final String EXKEY_JDA = "jda.jda";

	public static final LuaLibrary<LuaLibraryJDA> INSTANCE = new LuaLibrary<>("jda", LuaLibraryJDA.class);
}