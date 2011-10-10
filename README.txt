LoLSim
======

This is a work in progress. It currently contains a working verison of an
engine that behaves very much like League of Legends does. All of the
functionality is there, but as there are a TON of champions and items to
create, it currently has most of the items, Tristana, and half of Rammus.

I'd also like to remind you that LoL is maintained by a full company and I did
what's in this repo in two weekends by myself.

ANYWAY, the purpose of this project is to find the best possilbe 1v1 item
build, taking into account things like stuns, slows, spell shields, abilities,
and more.

The program has a representation of a champion, and each champion has an AI.
When the program runs, it has two champions fight each other with every
possible combination of items, then ranks the best item builds based on the
number of victories and the average health remaining.

Yes, this is a *lot* of possibilities. Tristana vs. Tristana using only
physical DPS items is about 12 million battles to simulate. And yes, it runs
in parallel.


Running the Code
================

This will be easiest if you unpack the whole thing into an Eclipse project.

What you want to run is simulation.LolSim. Because I haven't gotten a nice GUI
yet, you have to edit the items that a champion is tested with in that champ's
file. You can edit the other parameters in LolSim.java.


Updates
=======

While this is currently a fully working engine, it doesn't have all the
champions and is also missing some items (it was created before tenacity items,
but guess what? I wrote tenacity in there anyway before they came out. Go me!).
Doing data entry and writing AI for the other 82 champions is not going to be
fun, so this might be a while until it's completed. Let me know if you want me
to do a particular champion next.

What will ACTUALLY be coming next is comments! Lots of them. And also an easy
way to build the project, and to change parameters.
