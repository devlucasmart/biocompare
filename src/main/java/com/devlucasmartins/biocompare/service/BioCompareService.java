package com.devlucasmartins.biocompare.service;

@Service
public class BioCompareService {
    private static final String BLAST_EXECUTABLE = "/usr/bin/blastn"; // Caminho para o executável do BLAST
    private static final String DB_PATH = "/path/to/blast/db/nt"; // Banco de dados genômico

    public String runBlastFromFile(MultipartFile file) throws IOException, InterruptedException {
        // Criar um arquivo temporário
        File tempFile = File.createTempFile("uploaded_genome", ".fasta");
        file.transferTo(tempFile);

        // Comando BLAST
        ProcessBuilder pb = new ProcessBuilder(
                BLAST_EXECUTABLE, "-query", tempFile.getAbsolutePath(), "-db", DB_PATH, "-outfmt", "6"
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // Ler a saída do BLAST
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        process.waitFor();
        tempFile.delete(); // Remover o arquivo temporário
        return output.toString();
    }
}
