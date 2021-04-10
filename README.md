# AndroidListViewTemplate

此專案展示了使用 `BaseAdapter` 簡單設計一個 `ListView` 的介面，同時也展示了如何在同一個 `ListView` 中顯示不同佈局，達到群組化的效果。


```java
public class ItemAdapter extends BaseAdapter {

    private final int ITEM_TYPE_COUNT = 2;
    private final int ITEM_TYPE_DATA = 0;
    private final int ITEM_TYPE_HEADER = 1;

    private final List<ItemData> dataList;
    private final LayoutInflater mInflater;

    public ItemAdapter(Context context, List<ItemData> dataList) {
        mInflater = getSystemService(context, LayoutInflater.class);
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position) instanceof ItemHeader
                ? ITEM_TYPE_HEADER : ITEM_TYPE_DATA;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_TYPE_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        int itemType = getItemViewType(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            switch (itemType) {
                case ITEM_TYPE_HEADER:
                    convertView = mInflater.inflate(R.layout.header_item, null);
                    viewHolder.txtHeader = convertView.findViewById(R.id.txtHeader);
                    break;

                case ITEM_TYPE_DATA:
                    convertView = mInflater.inflate(R.layout.data_item, null);
                    viewHolder.txtTitle = convertView.findViewById(R.id.txtTitle);
                    viewHolder.txtSubtitle = convertView.findViewById(R.id.txtSubtitle);
                    break;
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        switch (itemType) {
            case ITEM_TYPE_HEADER:
                ItemHeader header = (ItemHeader) dataList.get(position);
                viewHolder.txtHeader.setText(header.header);
                break;

            case ITEM_TYPE_DATA:
                ItemData data = dataList.get(position);
                viewHolder.txtTitle.setText(data.title);
                viewHolder.txtSubtitle.setText(data.subtitle);
                break;

        }
        return convertView;
    }

    class ViewHolder {
        TextView txtHeader;
        TextView txtTitle;
        TextView txtSubtitle;
    }
}
```
