package com.hk.lua.jda;

import com.hk.lua.*;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.internal.entities.EntityBuilder;
import org.jetbrains.annotations.NotNull;

public class LuaUser extends LuaUserdata
{
	private final User user;

	public LuaUser(User user)
	{
		this.user = user;

		metatable = userMetatable;
		metatable.rawSet("id", user.getIdLong());
	}

	private static LuaObject getName(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaUser))
			throw Lua.badArgument(0, "getName", "expected USER*");

		LuaUser user = (LuaUser) args[0];
		return Lua.newString(user.user.getName());
	}

	private static LuaObject openPrivateChannel(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaUser))
			throw Lua.badArgument(0, "openPrivateChannel", "expected USER*");

		LuaUser user = (LuaUser) args[0];
		return new LuaMessageChannel(user.user.openPrivateChannel().complete());
	}

	@Override
	@NotNull
	public String name()
	{
		return "USER*";
	}

	@Override
	public User getUserdata()
	{
		return user;
	}

	private static final LuaObject userMetatable;

	static
	{
		userMetatable = Lua.newTable();
		userMetatable.rawSet("__name", "USER*");
		userMetatable.rawSet("__index", userMetatable);

		userMetatable.rawSet("getName", Lua.newMethod(LuaUser::getName));
		userMetatable.rawSet("openPrivateChannel", Lua.newMethod(LuaUser::openPrivateChannel));
	}
}
