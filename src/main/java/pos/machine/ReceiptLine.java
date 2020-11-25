package pos.machine;

public class ReceiptLine {
    private ItemInfo item;
    private Integer amount;
    private Integer subtotal;

    public ReceiptLine(ItemInfo item, Integer amount) {
        this.item = item;
        this.amount = amount;
        this.subtotal = item.getPrice() * amount;
    }

    public ItemInfo getItem() {
        return item;
    }

    public void setItem(ItemInfo item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public String generateDisplayLine(){
        return "Name: " + item.getName() + ", Quantity: " + amount + ", Unit price: " + item.getPrice() + " (yuan), Subtotal: " + getSubtotal() + " (yuan)\n";
    }
}
