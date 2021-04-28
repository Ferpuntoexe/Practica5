package net.iesseveroochoa.FernandoMartinezPerez.practica5.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import net.iesseveroochoa.FernandoMartinezPerez.practica5.R;
import net.iesseveroochoa.FernandoMartinezPerez.practica5.model.Dia;

import java.util.List;

public class DiaAdapter extends RecyclerView.Adapter<DiaAdapter.DiaViewHolder> {

    private List<Dia> listaDias;
    private OnItemClickBorrarListener listenerBorrar;
    private OnItemClickEditarListener listenerEditar;

    public void setListaDias(List<Dia> dias){
        listaDias = dias;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dia, parent, false);
        return new DiaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaViewHolder holder, int position) {
        if (listaDias != null) {
            final Dia dia = listaDias.get(position);

            holder.tvResumen.setText(dia.getId() + "-" + dia.getResumen());
            holder.tvFecha.setText(dia.getId() + "-" + dia.getFecha());


        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DiaViewHolder extends RecyclerView.ViewHolder {

        private TextView tvResumen;
        private TextView tvFecha;
        private ImageView ivEstado;
        private ImageView ivEditar;
        private ImageView ivBorrar;
        private ConstraintLayout clItem;

        public DiaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResumen = itemView.findViewById(R.id.tvResumen);
            tvFecha = itemView.findViewById(R.id.tvFechaRes);
            ivEstado = itemView.findViewById(R.id.ivEstado);
            ivEditar = itemView.findViewById(R.id.ivEditar);
            ivEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerEditar.onItemClickEditar(listaDias.get(DiaViewHolder.this.getAdapterPosition()));
                }
            });

            /**Aqui se escucha si se ha pulsado sobre el icono de Borrar*/
            ivBorrar = itemView.findViewById(R.id.ivBorrar);
            ivBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerBorrar.onItemClickBorrar(listaDias.get(DiaViewHolder.this.getAdapterPosition()));

                }
            });
            clItem = itemView.findViewById(R.id.clItem);
        }

    }




    public interface OnItemClickBorrarListener {
        void onItemClickBorrar(Dia dia);

    }

    public interface OnItemClickEditarListener {
        void onItemClickEditar(Dia dia);
    }

    public void setOnItemClickBorrarListener(OnItemClickBorrarListener listener) {
        this.listenerBorrar = listener;

    }

    public void setOnItemClickEditarListener(OnItemClickEditarListener listener) {
        this.listenerEditar = listener;

    }
}
