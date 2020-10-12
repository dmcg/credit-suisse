# NOTES TAKEN WHILE DOING THE EXERCISE

## UNCLEAR FROM THE SPEC

* Are we shipping a program or a library? 
* Top 10 buy OR sells or buy AND sells?
* There is a record of the market with all the trades?
* Can cancelling an order remove it if it wasn't in the top 10?
* What does live orders mean?  
* What about the missing coin types in the example?
* Should BUYs be netted against SELLs?
* What does registered mean?
* Can I cancel an order that is not on the board?
* Should the board show only one coin type or all?

## ASSUMPTIONS MADE 

I've made the following assumptions in order to get this ticket started.

* The board has a mode, so buy or sell but not both on the same board.
* Show only one coin type on each board. This made sense when I started from a UX perspsective, but I'm questioning it now. It would be very easy to change.
* We have a source of live orders, whatever live means.
* The source of orders has orders that are not necessarily on the board.
* We're going to ignore cancelling an order for now, because we don't know how to interpret it
* Not multicurrency for prices
* Top 10 means the top 10 lines on the board, not the top ten orders

# PROGRESS

The code here was a couple of hours work, proper TDD. I think that it's probably the sweet spot where I've a proof of concept and have worked through some of the issues, but won't have to rework potentially hard things when the ambiguities and assumptions are cleared up.

So I guess I'm saying is the point at which I'd go back to a BA and say - "Is this what you meant?" and we could chat about all those questions that maybe should have been answered before I started work.  
