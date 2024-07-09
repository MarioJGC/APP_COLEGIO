package pojo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.examenfinal.R;

import java.util.List;

public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.AsistenciaHolder> {

    List<Asistencia> listaAsistencia;


    public AsistenciaAdapter(List<Asistencia> listaAsistencia) {
            this.listaAsistencia = listaAsistencia;
            }

    @NonNull
    @Override
    public AsistenciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_asistencia, parent, false);
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.height = 200; // Establecer el alto deseado en píxeles
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            vista.setLayoutParams(layoutParams);


            return new AsistenciaHolder(vista);
            }

    @Override
    public void onBindViewHolder(@NonNull AsistenciaHolder holder, int position) {
            Asistencia asistencia = listaAsistencia.get(position);
            holder.txtAsistidos.setText(asistencia.getNombreEstudiante());
            holder.txtFecha.setText(asistencia.getFecha());
            holder.txtSeccion.setText(asistencia.getSeccion());
            }

    @Override
    public int getItemCount() {
            return listaAsistencia.size();
            }

    public class AsistenciaHolder extends RecyclerView.ViewHolder  {

        TextView txtAsistidos,txtFecha,txtSeccion;

        public AsistenciaHolder(@NonNull View itemView) {
            super(itemView);
            txtAsistidos = itemView.findViewById(R.id.txtAsistidos);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            txtSeccion = itemView.findViewById(R.id.txtSeccion);
        }


    }
}