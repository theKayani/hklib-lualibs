package com.hk.lua;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>This library allows the manipulation of UUIDs which includes
 * UUIDs from strings, to strings, from bits, and to bits.</p>
 */
public enum LuaLibraryUUID implements BiConsumer<Environment, LuaObject>, Lua.LuaMethod
{
	/** */
	_new() {
		@Override
		public LuaObject call(LuaInterpreter interp, LuaObject[] args)
		{
			switch (args.length)
			{
				case 1:
					Lua.checkArgs(toString(), args, LuaType.STRING);
					try
					{
						return new LuaUUID(UUID.fromString(args[0].getString()));
					}
					catch (IllegalArgumentException ex)
					{
						return Lua.wrapErr(ex);
					}
				case 2:
					Lua.checkArgs(toString(), args, LuaType.INTEGER, LuaType.INTEGER);
					return new LuaUUID(new UUID(args[0].getLong(), args[1].getLong()));
				default:
					throw new LuaException("uuid expects string or two integers");
			}
		}

		@Override
		public String toString()
		{
			return "new";
		}
	};

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

	private static LuaObject version(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaUUID))
			throw Lua.badArgument(0, "version", "expected UUID*");

		LuaUUID uuid = (LuaUUID) args[0];
		return Lua.newNumber(uuid.uuid.version());
	}

	private static LuaObject node(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaUUID))
			throw Lua.badArgument(0, "node", "expected UUID*");

		LuaUUID uuid = (LuaUUID) args[0];
		return Lua.newNumber(uuid.uuid.node());
	}

	private static LuaObject clockSequence(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaUUID))
			throw Lua.badArgument(0, "clockSequence", "expected UUID*");

		LuaUUID uuid = (LuaUUID) args[0];
		return Lua.newNumber(uuid.uuid.clockSequence());
	}

	private static LuaObject timestamp(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaUUID))
			throw Lua.badArgument(0, "timestamp", "expected UUID*");

		LuaUUID uuid = (LuaUUID) args[0];
		return Lua.newNumber(uuid.uuid.timestamp());
	}

	private static LuaObject variant(LuaInterpreter interp, LuaObject[] args)
	{
		if(!(args[0] instanceof LuaUUID))
			throw Lua.badArgument(0, "variant", "expected UUID*");

		LuaUUID uuid = (LuaUUID) args[0];
		return Lua.newNumber(uuid.uuid.variant());
	}

	private static final LuaObject uuidMetatable;

	static
	{
		uuidMetatable = Lua.newTable();
		uuidMetatable.rawSet("__name", "UUID*");
		uuidMetatable.rawSet("__index", uuidMetatable);

		uuidMetatable.rawSet("version", Lua.newMethod(LuaLibraryUUID::version));
		uuidMetatable.rawSet("node", Lua.newMethod(LuaLibraryUUID::node));
		uuidMetatable.rawSet("clockSequence", Lua.newMethod(LuaLibraryUUID::clockSequence));
		uuidMetatable.rawSet("timestamp", Lua.newMethod(LuaLibraryUUID::timestamp));
		uuidMetatable.rawSet("variant", Lua.newMethod(LuaLibraryUUID::variant));
	}

	/**
	 * This class encapsulates a {@link java.util.UUID} to be used
	 * within a Lua environment.
	 */
	public static class LuaUUID extends LuaUserdata
	{
		private final UUID uuid;

		/**
		 * This constructor creates a {@link LuaObject} that
		 * encapsulates a Java UUID.
		 *
		 * @param uuid a non-null UUID value
		 */
		public LuaUUID(@NotNull UUID uuid)
		{
			this.uuid = Objects.requireNonNull(uuid);

			metatable = uuidMetatable;
			metatable.rawSet("lsb", uuid.getLeastSignificantBits());
			metatable.rawSet("msb", uuid.getMostSignificantBits());
		}

		@NotNull
		@Override
		public String getString(@Nullable LuaInterpreter interp)
		{
			return uuid.toString();
		}

		@NotNull
		@Override
		public String name()
		{
			return "UUID*";
		}

		@Override
		public UUID getUserdata()
		{
			return uuid;
		}
	}
}
