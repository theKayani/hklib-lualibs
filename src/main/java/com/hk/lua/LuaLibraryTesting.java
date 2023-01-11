package com.hk.lua;

import com.hk.func.BiConsumer;

/**
 * This library contains various methods to help with unit testing.
 * This library can also execute various files within a directory, etc.
 */
public enum LuaLibraryTesting implements BiConsumer<Environment, LuaObject>, Lua.LuaMethod
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