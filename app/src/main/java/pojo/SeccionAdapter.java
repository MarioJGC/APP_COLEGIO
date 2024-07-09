package pojo;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.examenfinal.AsignaturasProfesor;
import com.gm.examenfinal.R;

import java.util.List;

public class SeccionAdapter extends RecyclerView.Adapter<SeccionAdapter.SeccionHolder>{

    List<Seccion> listaSeccion;

    public SeccionAdapter(List<Seccion> listaSeccion) {
        this.listaSeccion = listaSeccion;
    }

    @NonNull
    @Override
    public SeccionHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cursos2, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = 435; // Establecer el alto deseado en píxeles
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        vista.setLayoutParams(layoutParams);
        return new SeccionHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull SeccionHolder holder, int position) {
        Seccion seccion = listaSeccion.get(position);
        holder.button.setText(seccion.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return listaSeccion.size();
    }

    public class SeccionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button button;
        public SeccionHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btnCursos);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {

                Seccion seccion = listaSeccion.get(position);

                Log.d("SeccionAdapter", "id_seccion: " + seccion.getId_seccion());
                Log.d("SeccionAdapter", "descripcion: " + seccion.getDescripcion());

                // Crear un Intent para iniciar la actividad Actividades
                Intent i = new Intent(itemView.getContext(), AsignaturasProfesor.class);

                // Pasar la actividad como extra al Intent
                i.putExtra("seccion", seccion);

                // Iniciar la actividad
                itemView.getContext().startActivity(i);
            }
        }
    }
}