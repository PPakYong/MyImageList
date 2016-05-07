package myimagelist.yhpark.com.myimagelist;

/**
 * Created by YHPark on 2016-05-07.
 */
@Deprecated
public class RecyclerAdapter {}/*extends RecyclerView.Adapter<ViewHolder> {
    private List<ImageObject> item = new ArrayList<ImageObject>();
    private Context context = null;

    RecyclerAdapter(Context context, List<ImageObject> item) {
        this.item = item;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_image, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(item.get(position).getImgUrl()).fitCenter().into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public List<ImageObject> getItem() {
        return item;
    }
}*/
