package com.hk.lua;

import com.hk.Assets;
import com.hk.func.Consumer;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;

public class LuaTest
{
	@Test
	public void librarySocket() throws FileNotFoundException
	{
		testLibrary(LuaLibraries.SOCKET);
	}

	@Test
	public void libraryHTTP() throws FileNotFoundException
	{
		testLibrary(LuaLibraries.HTTP);
	}

	@Test
	public void libraryFileSystem() throws FileNotFoundException
	{
		testLibrary(LuaLibraries.FILESYSTEM);
	}

	@Test
	public void libraryUUID() throws FileNotFoundException
	{
		testLibrary(LuaLibraries.UUID);
	}

	@Test
	public void libraryG2D() throws FileNotFoundException
	{
		testLibrary(new LuaLibrary<>("g2d", LuaLibraryG2D.class));
	}

	@Test
	public void librarySwing() throws FileNotFoundException
	{
		testLibrary(new LuaLibrary<>("swing", LuaLibrarySwing.class));
	}

	private static void testLibrary(final LuaLibrary<?> library) throws FileNotFoundException
	{
		testLibrary(library.table, interp -> interp.importLib(library));
	}

	private static void testLibrary(String file, Consumer<LuaInterpreter> consumer) throws FileNotFoundException
	{
		final LuaInterpreter interp = Lua.reader(Assets.get("lua/library_" + file + ".lua"));

		interp.setExtra(LuaLibraryPackage.EXKEY_PATH, "./src/test/resources/assets/lua/?.lua;./src/test/resources/assets/lua/?/init.lua");

		Lua.importStandard(interp);
		interp.importLib(LuaLibrary.PACKAGE);
		consumer.accept(interp);

		Object obj = interp.execute();

		assertTrue(obj instanceof LuaObject);
		assertTrue(((LuaObject) obj).getBoolean());
	}
}
