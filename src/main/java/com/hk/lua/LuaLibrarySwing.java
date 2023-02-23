package com.hk.lua;

import java.util.function.BiConsumer;

/**
 * This library opens the {@link javax.swing} package to easily
 * manipulate JFrames, JPanels, and JComponents. <em>Can be used with</em>
 * {@link LuaLibraryG2D} <em>to create "custom" components.</em>
 */
public enum LuaLibrarySwing implements BiConsumer<Environment, LuaObject>, Lua.LuaMethod
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
