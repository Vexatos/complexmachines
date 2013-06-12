package pixlepix.complexmachines.common.tileentity;

import pixlepix.complexmachines.common.CoordTuple;

public class Link {
	CoordTuple origin;
	CoordTuple destination;
	int originSide;
	int destinationSide;
	public Link(CoordTuple origin, int originSide, CoordTuple destination, int destinationSide){
		this.origin=origin;
		this.destination=destination;
		this.originSide=originSide;
		this.destinationSide=destinationSide;
	}
}
