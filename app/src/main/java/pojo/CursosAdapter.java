package pojo;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.examenfinal.ActividadEstudiante;
import com.gm.examenfinal.R;

import java.util.List;

public class CursosAdapter extends
        RecyclerView.Adapter<CursosAdapter.CursosHolder> {

    List<Curso> listaCursos;

    public CursosAdapter(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    @NonNull
    @Override
    public CursosAdapter.CursosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cursos, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = 435; // Establecer el alto deseado en píxeles
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        vista.setLayoutParams(layoutParams);
        return new CursosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CursosAdapter.CursosHolder holder, int position) {
        Curso curso = listaCursos.get(position);
        holder.button.setText(curso.getNombre_curso());

    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public class CursosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button button;

        public CursosHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btnCursos);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Curso curso = listaCursos.get(position);

                // Imprimir los valores de los datos del curso
                Log.d("CursosAdapter", "id_curso: " + curso.getId_curso());
                Log.d("CursosAdapter", "id_seccion: " + curso.getId_seccion());

                // Crear un Intent para iniciar la actividad ActividadEstudiante
                Intent intent = new Intent(itemView.getContext(), ActividadEstudiante.class);

                // Pasar los datos del curso como extras al Intent
                intent.putExtra("curso",curso);
                // Agregar aquí más datos del curso si es necesario

                // Iniciar la actividad
                itemView.getContext().startActivity(intent);
            }
        }
    }
}