package com.hk.lua.jda;

import com.hk.Assets;
import com.hk.file.FileUtil;
import com.hk.lua.LuaLibrary;
import com.hk.lua.LuaTest;
import junit.framework.TestSuite;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class LuaLibraryJDATest extends ListenerAdapter
{
	@Test
	public void libraryJDA() throws FileNotFoundException, InterruptedException
	{
		File tokenFile = Assets.get("discord_token.txt");

		if(!tokenFile.exists())
		{
			Assert.fail("no token");
			return;
		}

		String token = FileUtil.getFileContents(tokenFile);

		JDA jda = JDABuilder.createDefault(token)
				.enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
				.build();
		jda.addEventListener(this);
		jda.awaitReady();

		LuaLibrary<LuaLibraryJDA> library = LuaLibraryJDA.INSTANCE;
		LuaTest.testLibrary("jda", interpreter -> {
			interpreter.setExtra(LuaLibraryJDA.EXKEY_JDA, jda);
			interpreter.importLib(library);
		});
	}
}