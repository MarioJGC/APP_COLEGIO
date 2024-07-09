package pojo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.examenfinal.ActividadEstudiante;
import com.gm.examenfinal.AgregarEntrega;
import com.gm.examenfinal.R;

import java.util.Calendar;
import java.util.List;

public class ActividadAdapter extends
        RecyclerView.Adapter<ActividadAdapter.ActividadHolder>{

    List<Actividad> listaActividad;

    public ActividadAdapter(List<Actividad> listaActividad) {
        this.listaActividad = listaActividad;
    }

    @NonNull
    @Override
    public ActividadAdapter.ActividadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cursos2, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = 435; // Establecer el alto deseado en píxeles
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        vista.setLayoutParams(layoutParams);
        return new ActividadAdapter.ActividadHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadAdapter.ActividadHolder holder, int position) {
        Actividad actividad = listaActividad.get(position);
        holder.button.setText(actividad.getTitulo());

        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // Lógica para seleccionar el recurso de icono según la fecha actual
        /*
        if (dayOfMonth == 30 && month == Calendar.JUNE && year == 2023) {
            holder.button.setIcon(R.drawable.ic_back);
        } else {
            holder.button.setIcon(R.drawable.ic_icono_predeterminado);
        }*/

    }

    @Override
    public int getItemCount() {
        return listaActividad.size();
    }

    public class ActividadHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button button;
        public ActividadHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btnCursos);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {

                // Obtener la actividad correspondiente a la posición
                Actividad actividad = listaActividad.get(position);

                // Crear un Intent para iniciar la actividad Actividades
                Intent intent = new Intent(itemView.getContext(), AgregarEntrega.class);

                // Pasar la actividad como extra al Intent
                intent.putExtra("actividad", actividad);

                // Iniciar la actividad
                itemView.getContext().startActivity(intent);
            }
        }
    }
}