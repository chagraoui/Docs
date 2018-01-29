package org.tux.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.tux.dao.PersonneRepository;
import org.tux.entites.Personne;

@Configuration
@EnableBatchProcessing
public class batchConfiguration {

	private Logger logger = Logger.getLogger(batchConfiguration.class);

	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
    
    @Autowired
    public PlatformTransactionManager transactionManager;
    
    @Autowired
    public JobRepository jobRepository;
    
    @Autowired
    public PersonneRepository personneRepository;
    
	@Bean(name="taskExecutor")
	public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor(){
		SimpleAsyncTaskExecutor sate = new SimpleAsyncTaskExecutor();
		return sate;
	}
    
	@Bean(name="simpleJobLauncher")
	public SimpleJobLauncher simpleJobLauncher(){
		SimpleJobLauncher sjl = new SimpleJobLauncher();
		sjl.setJobRepository(jobRepository);
		sjl.setTaskExecutor(simpleAsyncTaskExecutor());
		return sjl;
	}
	
    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Personne> reader() {
        FlatFileItemReader<Personne> reader = new FlatFileItemReader<Personne>();
        reader.setResource(new ClassPathResource("listePersonne.csv"));
        reader.setLineMapper(new DefaultLineMapper<Personne>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] {"prenom","nom" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Personne>() {{
                setTargetType(Personne.class);
            }});
        }});
        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Personne> writer() {
        JdbcBatchItemWriter<Personne> writer = new JdbcBatchItemWriter<Personne>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Personne>());
        writer.setSql("INSERT INTO personne (prenom, nom) VALUES (:prenom,:nom)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

 // tag::jobstep[]
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Personne, Personne> chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
    
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }
}
