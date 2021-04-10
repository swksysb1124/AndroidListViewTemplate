package st.jason.mutligrouplistviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvAItems = findViewById(R.id.lvAItems);
        List<ItemData> dataAList = createItemDataAndHeader(getOriginalItemData());
        ItemAdapter adapterA = new ItemAdapter(this, dataAList);
        lvAItems.setAdapter(adapterA);
        lvAItems.setOnItemClickListener((parent, view, position, id) -> {
            ItemData data = dataAList.get(position);
            if (!(data instanceof ItemHeader)) {
                Toast.makeText(MainActivity.this, "data: " + data.title, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private List<ItemData> getOriginalItemData() {
        List<ItemData> originalData = new ArrayList<>();
        originalData.add(new ItemData("title1", "GroupA"));
        originalData.add(new ItemData("title8", "GroupB"));
        originalData.add(new ItemData("title2", "GroupA"));
        originalData.add(new ItemData("title9", "GroupB"));
        originalData.add(new ItemData("title3", "GroupA"));
        originalData.add(new ItemData("title10", "GroupB"));
        originalData.add(new ItemData("title4", "GroupA"));
        originalData.add(new ItemData("title11", "GroupB"));
        originalData.add(new ItemData("title5", "GroupA"));
        originalData.add(new ItemData("title12", "GroupB"));
        originalData.add(new ItemData("title6", "GroupA"));
        originalData.add(new ItemData("title13", "GroupB"));
        originalData.add(new ItemData("title14", "GroupB"));
        originalData.add(new ItemData("title15", "GroupB"));
        return originalData;
    }

    private List<ItemData> createItemDataAndHeader(List<ItemData> originalData) {
        List<ItemData> groupA = new ArrayList<>();
        groupA.add(new ItemHeader("GroupA"));
        for (ItemData data : originalData) {
            if ("GroupA".equals(data.subtitle)) {
                groupA.add(data);
            }
        }

        List<ItemData> groupB = new ArrayList<>();
        groupB.add(new ItemHeader("GroupB"));
        for (ItemData data : originalData) {
            if ("GroupB".equals(data.subtitle)) {
                groupB.add(data);
            }
        }

        List<ItemData> dataAList = new ArrayList<>();
        dataAList.addAll(groupA);
        dataAList.addAll(groupB);
        return dataAList;
    }
}