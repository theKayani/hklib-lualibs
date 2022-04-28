print = function() end
_ENV = require('lunity')('http library')

function test:table()
    assertType(http, 'table')
end

local allPassed = test{
    useANSI=false, -- turn off ANSI codes (colors/bold) in the output
    useHTML=false,  -- turn on  HTML markup in the output
    quiet=true,    -- silence print() and io.write() other than Lua during the tests
}

assert(allPassed)

return true