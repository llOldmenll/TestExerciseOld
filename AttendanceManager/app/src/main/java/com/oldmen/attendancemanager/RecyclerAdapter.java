package com.oldmen.attendancemanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import io.realm.RealmResults;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private RealmResults<Student> students;
    private StudentStatusChange stdStatusInterface;

    public RecyclerAdapter(RealmResults<Student> students) {
        this.students = students;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        stdStatusInterface = (StudentStatusChange) parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.bindView(students.get(position));

        holder.btnIntime.setOnClickListener(createListener(Const.IN_TIME, position));
        holder.btnLated.setOnClickListener(createListener(Const.LATED, position));
        holder.btnNotCame.setOnClickListener(createListener(Const.NOT_CAME, position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    private View.OnClickListener createListener(final String newState, final int position) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    stdStatusInterface.onStatusChanged(students.get(position), newState);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                }catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        };
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {

        private TextView studentName;
        private CircularImageView studentImage;
        private ImageButton btnIntime;
        private ImageButton btnLated;
        private ImageButton btnNotCame;

        public RecyclerHolder(View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.student_name_item);
            studentImage = itemView.findViewById(R.id.student_image_item);
            btnIntime = itemView.findViewById(R.id.intime_btn_item);
            btnLated = itemView.findViewById(R.id.lated_btn_item);
            btnNotCame = itemView.findViewById(R.id.not_came_btn_item);
        }

        void bindView(Student std) {

            studentName.setText(std.getName());
            studentImage.setImageResource(std.getImgId());

            switch (std.getState()) {

                case Const.UNMARKED:
                    btnIntime.setImageResource(R.drawable.ic_check_grey);
                    btnLated.setImageResource(R.drawable.ic_clock_grey);
                    btnNotCame.setImageResource(R.drawable.ic_x_grey);
                    break;

                case Const.IN_TIME:
                    btnIntime.setImageResource(R.drawable.ic_check_green);
                    btnIntime.setFocusable(false);
                    btnLated.setImageResource(R.drawable.ic_clock_grey);
                    btnNotCame.setImageResource(R.drawable.ic_x_grey);
                    break;

                case Const.LATED:
                    btnIntime.setImageResource(R.drawable.ic_check_grey);
                    btnLated.setImageResource(R.drawable.ic_clock_orange);
                    btnLated.setFocusable(false);
                    btnNotCame.setImageResource(R.drawable.ic_x_grey);
                    break;

                case Const.NOT_CAME:
                    btnIntime.setImageResource(R.drawable.ic_check_grey);
                    btnLated.setImageResource(R.drawable.ic_clock_grey);
                    btnNotCame.setImageResource(R.drawable.ic_x_red);
                    btnNotCame.setFocusable(false);
                    break;
            }
        }

    }

}
