print = function() end
_ENV = require('lunity')('files library')

function test:table()
    assertType(lfs, 'table')
end

local allPassed = test{
    useANSI=false, -- turn off ANSI codes (colors/bold) in the output
    useHTML=false,  -- turn on  HTML markup in the output
    quiet=true,    -- silence print() and io.write() other than Lua during the tests
}

assert(allPassed)

return true