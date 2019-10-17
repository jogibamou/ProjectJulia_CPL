begin 

	println("\n*******************************\n* Julia Test Program Starting *\n*******************************\n")

	x = 1 
	y = 2
	z = 3
	x = +x
	a = x + y 
	b = z - y
	c = x * y
	#This is a comment
	#= This is a  multiline comment =#
	d = z ^ y
	e = z / x
	f = x \ y
	g = ( x + y ) / x
	h = z % y
	println( x == 1 )
	println(true || false)
	println(false && true)
	print( h <= g )
	print( h <= g )
	print( e >= g )
	print( x > y )
	print( y < z )
	print( y != z )

	function test_RSVP_IF()
		if(true == true)
			println("PASS RSVP_IF: \"if\"")
		end
	end

	function test_RSVP_WHIL()
		while x < 5
			println(x)
			global x += x
		end
    end

    function test_RSVP_FOR()
		for i in [43, 3, 16]
			println(i)
		end
    end

	test_RSVP_IF()
	println("PASS RSVP_IF: \"if\"")
	test_RSVP_WHIL()
	println("PASS RSVP_WHIL: \"while\"")
	test_RSVP_FOR() 		
	println("PASS RSVP_FOR: \"for\"")

	
    println("\nAll test complete")
	
end
