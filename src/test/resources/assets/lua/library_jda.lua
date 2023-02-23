--print = function() end
_ENV = require('lunity')('Java Discord API library')

function test:table()
    assertType(jda, 'table')
    assertTrue(jda.isConnected())
end

function test:channel()
    local testChannel = jda.getTextChannel(1078074270174941214)
    assertNotNil(testChannel)
    assertEqual('jda-tester', testChannel:getName())

    assertNil(jda.getTextChannel(-1))

    assertNotNil(testChannel:sendMessage('Hello!'))

    local msg = testChannel:sendMessage('abc')
    assertNotNil(msg)
    msg = msg:reply('123')
    assertNotNil(msg)
    msg = msg:reply('come with me')
    assertNotNil(msg)

    assertEqual('jda-tester', msg:getChannel().getName())
    assertEqual(testChannel.id, msg:getChannel().id)
end

local allPassed = test{
    useANSI=false, -- turn off ANSI codes (colors/bold) in the output
    useHTML=false,  -- turn on  HTML markup in the output
    quiet=true,    -- silence print() and io.write() other than Lua during the tests
}

assert(allPassed)

return true