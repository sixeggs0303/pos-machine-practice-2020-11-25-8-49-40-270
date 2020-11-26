package pos.machine;

import java.util.List;

public class PosMachine {

    public String printReceipt(List<String> barcodes) {
        int total = 0;
        StringBuilder receipt = setReceiptHeader();

        List<ItemInfo> allItemInfo = ItemDataLoader.loadAllItemInfos();

        String[] distinctBarcodes = barcodes.stream().distinct().toArray(String[]::new);

        for (String barcode : distinctBarcodes) {
            ItemInfo itemInfo = findItemByBarcode(allItemInfo, barcode);
            ReceiptLine receiptLine = new ReceiptLine(itemInfo, countItem(barcodes, barcode));
            receipt.append(receiptLine.generateDisplayLine());
            total += receiptLine.getSubtotal();
        }

        return finishReceipt(receipt, total);
    }

    private int countItem(List<String> barcodes, String targetBarcode) {
        return barcodes.stream().filter(barcode -> barcode.equals(targetBarcode)).toArray().length;
    }

    private ItemInfo findItemByBarcode(List<ItemInfo> allItemInfo, String barcode) {
        return allItemInfo.stream().filter(item -> item.getBarcode().equals(barcode)).findFirst().orElse(null);
    }

    private StringBuilder setReceiptHeader() {
        return new StringBuilder("***<store earning no money>Receipt***\n");
    }

    private String finishReceipt(StringBuilder receipt, Integer total) {
        return receipt
                .append("----------------------\nTotal: ")
                .append(total)
                .append(" (yuan)\n**********************")
                .toString();
    }
}