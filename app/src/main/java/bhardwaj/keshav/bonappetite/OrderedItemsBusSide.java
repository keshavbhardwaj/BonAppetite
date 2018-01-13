package bhardwaj.keshav.bonappetite;

import java.util.List;

/**
 * Created by keshav on 11/1/18.
 */

public class OrderedItemsBusSide {

    public String getItemName() {
        return ItemName;
    }

    public OrderedItemsBusSide(String itemName, String itemCount, String seatNo) {
        ItemName = itemName;
        ItemCount = itemCount;
        SeatNo = seatNo;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemCount() {
        return ItemCount;
    }

    public void setItemCount(String itemCount) {
        ItemCount = itemCount;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String seatNo) {
        SeatNo = seatNo;
    }

    public String ItemName, ItemCount, SeatNo;

}
