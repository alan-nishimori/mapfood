package mapfood.converter.motoboy;

import mapfood.model.motoboy.Motoboy;
import mapfood.model.motoboy.MotoboyWithDistance;

public class MotoboyWithDistanceToMotoboy {

    private final MotoboyWithDistance motoboyWithDistance;

    public MotoboyWithDistanceToMotoboy(final MotoboyWithDistance motoboyWithDistance) {
        this.motoboyWithDistance = motoboyWithDistance;
    }

    public Motoboy build() {
        Motoboy motoboy = new Motoboy();
        motoboy.setId(motoboyWithDistance.getId());
        motoboy.setMotoboyStatus(motoboyWithDistance.getMotoboyStatus());
        motoboy.setLocation(motoboyWithDistance.getLocation());
        return motoboy;
    }
}
