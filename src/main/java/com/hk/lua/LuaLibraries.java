package com.hk.lua;

/**
 * <p>This class contains all the different packages that have been
 * implemented into a Lua library. They also have their own respective
 * tables, if needs be, a new <code>LuaLibrary</code> object can be
 * created to replace the imported table name.</p>
 *
 * <p>This class <em>doesn't</em> contain {@link com.hk.lua.LuaLibraryG2D}
 * or {@link com.hk.lua.LuaLibrarySwing} in the off chance that they
 * don't have the proper classes/resources to initialize. Also not
 * every environment that would be imported into would contain those
 * "requirements"</p>
 */
public final class LuaLibraries
{
    //******************************************** INDEPENDENT *********************************************/
    /** Constant <code>SOCKET</code> */
    public static final LuaLibrary<LuaLibrarySocket> SOCKET = new LuaLibrary<>("socket", LuaLibrarySocket.class);
    /** Constant <code>HTTP</code> */
    public static final LuaLibrary<LuaLibraryHTTP> HTTP = new LuaLibrary<>("http", LuaLibraryHTTP.class);
    /** Constant <code>FILESYSTEM</code> */
    public static final LuaLibrary<LuaLibraryFileSystem> FILESYSTEM = new LuaLibrary<>("lfs", LuaLibraryFileSystem.class);

    //******************************************** DEPENDENT *********************************************/
//    /** Constant <code>G2D</code> */
//    public static final LuaLibrary<LuaLibraryG2D> G2D = new LuaLibrary<>("g2d", LuaLibraryG2D.class);
//    /** Constant <code>SWING</code> */
//    public static final LuaLibrary<LuaLibrarySwing> SWING = new LuaLibrary<>("swing", LuaLibrarySwing.class);

    /**
     * <p>Import the standard <code>Lua 5.3</code> libraries, that come
     * with <code>hklib</code>, as well as the additional libraries
     * within this package.</p>
     * <p>Additional Libraries are:</p>
     * <ul>
     *     <li>{@link #SOCKET}</li>
     *     <li>{@link #HTTP}</li>
     *     <li>{@link #FILESYSTEM}</li>
     * </ul>
     *
     * @see Lua#importStandard(LuaInterpreter)
     * @param interp The environment to import the libraries into
     */
    public static void importAll(LuaInterpreter interp)
    {
        Lua.importStandard(interp);
        importOnlyExtra(interp);
    }

    /**
     * <p>Import only the extra libraries within this package.</p>
     * <p>Additional Libraries are:</p>
     * <ul>
     *     <li>{@link #SOCKET}</li>
     *     <li>{@link #HTTP}</li>
     *     <li>{@link #FILESYSTEM}</li>
     * </ul>
     *
     * @param interp The environment to import the libraries into
     */
    public static void importOnlyExtra(LuaInterpreter interp)
    {
        interp.importLib(SOCKET);
        interp.importLib(HTTP);
        interp.importLib(FILESYSTEM);
    }

    /**
     * <p>Import the standard <code>Lua 5.3</code> libraries, that come
     * with <code>hklib</code>, as well as the additional libraries
     * within this package.</p>
     * <p>Once these are imported into the factory, they don't need to
     * be imported into the retrieved interpreter object.</p>
     * <p>Additional Libraries are:</p>
     * <ul>
     *     <li>{@link #SOCKET}</li>
     *     <li>{@link #HTTP}</li>
     *     <li>{@link #FILESYSTEM}</li>
     * </ul>
     *
     * @see Lua#importStandard(LuaFactory)
     * @param factory The environment to import the libraries into
     */
    public static void importAll(LuaFactory factory)
    {
        Lua.importStandard(factory);
        importOnlyExtra(factory);
    }

    /**
     * <p>Import only the extra libraries within this package.</p>
     * <p>Additional Libraries are:</p>
     * <ul>
     *     <li>{@link #SOCKET}</li>
     *     <li>{@link #HTTP}</li>
     *     <li>{@link #FILESYSTEM}</li>
     * </ul>
     *
     * @param factory The environment to import the libraries into
     */
    public static void importOnlyExtra(LuaFactory factory)
    {
        factory.addLibrary(SOCKET);
        factory.addLibrary(HTTP);
        factory.addLibrary(FILESYSTEM);
    }

    private LuaLibraries()
    {}
}
