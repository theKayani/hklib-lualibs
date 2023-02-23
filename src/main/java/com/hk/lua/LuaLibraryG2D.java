package com.hk.lua;

import java.util.function.BiConsumer;

/**
 * This library uses the {@link com.hk.util.G2D} class to pipe all
 * graphic commands to a retrieving object.
 */
public enum LuaLibraryG2D implements BiConsumer<Environment, LuaObject>, Lua.LuaMethod
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
