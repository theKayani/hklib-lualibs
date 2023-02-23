package com.hk.lua;

import java.util.function.BiConsumer;

/**
 * This library allows HTTP/S requests to be made within the Lua
 * environment. Also with the ability to alter the request in many
 * ways.
 */
public enum LuaLibraryHTTP implements BiConsumer<Environment, LuaObject>, Lua.LuaMethod
{
	;

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
		String name = toString();
		if(name != null && !name.trim().isEmpty())
			table.rawSet(new LuaString(name), Lua.newFunc(this));
	}
}
