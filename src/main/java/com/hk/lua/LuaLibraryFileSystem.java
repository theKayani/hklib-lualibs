package com.hk.lua;

import java.util.function.BiConsumer;

/**
 * This library allows the usage of Java Files, Paths, and Iterators
 * to efficiently and effectively retrieve file and directory info.
 */
public enum LuaLibraryFileSystem implements BiConsumer<Environment, LuaObject>, Lua.LuaMethod
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
